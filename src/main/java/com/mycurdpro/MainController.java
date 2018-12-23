package com.mycurdpro;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Duang;
import com.jfinal.kit.HashKit;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.interceptor.PermissionInterceptor;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.utils.WebUtils;
import com.mycurdpro.common.utils.ws.UserIdEncryptUtils;
import com.mycurdpro.system.model.SysMenu;
import com.mycurdpro.system.model.SysOrg;
import com.mycurdpro.system.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


/**
 * 主页面
 *
 * @author zhangchuang
 */
@Clear(PermissionInterceptor.class)
public class MainController extends BaseController {

    private final static Logger LOG = LoggerFactory.getLogger(MainController.class);
    private final static MainService mainService = Duang.duang(MainService.class);

    public void index() {
        setAttr("username", WebUtils.getSysUser(this).getName());
        SysUser sysUser = WebUtils.getSysUser(this);
        setAttr("aesUserId", UserIdEncryptUtils.encrypt(sysUser.getId(), UserIdEncryptUtils.CURRENT_USER_ID_AESKEY));
        render("main.ftl");
    }


    /**
     * 树形菜单
     */
    public void menuTree() {
        SysUser sysUser = WebUtils.getSysUser(this);
        String roleIds = mainService.findRoleIdsByUserId(sysUser.getId());
        LOG.debug("{} has role ids {}", sysUser.getUsername(), roleIds);
        List<SysMenu> sysMenus = mainService.findUserMenus(roleIds);
        List<Map<String, Object>> maps = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", sysMenu.getId());
            map.put("pid", sysMenu.getPid());
            map.put("iconCls", sysMenu.getIcon());
            map.put("url", sysMenu.getUrl());
            map.put("text", sysMenu.getName());
            maps.add(map);
        }
        renderJson(maps);
    }

    /**
     * 修改用户密码
     */
    public void userPass() {
        SysUser sysUser = WebUtils.getSysUser(this);
        setAttr("sysUser", sysUser);
        render("userPass.ftl");
    }
    @Before(Tx.class)
    public void changePwd() {
        String oldPwd = getPara("oldPwd");
        String newPwd = getPara("newPwd");
        String reNewPwd = getPara("reNewPwd");
        
        if(StringUtils.isEmpty(oldPwd)){
            renderFail("请输入原密码");
            return;
        }
        if(StringUtils.isEmpty(newPwd)){
            renderFail("请输入新密码");
            return;
        }
        if(!Objects.equals(newPwd,reNewPwd)){
            renderFail("两次新密码不一致");
            return;
        }

        oldPwd = HashKit.sha1(oldPwd);
        SysUser curUser = WebUtils.getSysUser(this);
        SysUser sysUser = SysUser.dao.findByUsernameAndPassword(curUser.getUsername(), oldPwd);
        if (sysUser == null) {
            renderFail("旧密码错误");
            return;
        }
        if ("1".equals(sysUser.getUserState())) {
            renderFail("用户被禁用，无法修改密码");
            return;
        }

        newPwd = HashKit.sha1(newPwd);
        sysUser.setPassword(newPwd);
        boolean updateFlag = sysUser.update();
        addServiceLog(getPara("oldPwd") + " 修改为新密码：" + getPara("newPwd"));

        if (updateFlag) {
            renderSuccess("修改密码成功");
        } else {
            renderFail("修改密码失败");
        }
    }


    /**
     * 修改用户信息
     */
    public void userInfo(){
        SysUser sysUser = WebUtils.getSysUser(this);
        setAttr("sysUser", sysUser);
        SysOrg sysOrg = SysOrg.dao.findById(sysUser.getOrgId());
        if (sysOrg!=null){
            setAttr("orgName",sysOrg.getName());
        }
        render("userInfo.ftl");
    }
    public void changeUserInfo() {
        String id = getPara("userId");
        if (StringUtils.isEmpty(id)) {
            renderFail("参数错误");
            return;
        }
        SysUser sysUser = SysUser.dao.findById(id);
        if (sysUser == null) {
            renderFail("参数错误");
            return;
        }

        String avatar = getPara("avatar");
        if(StringUtils.notEmpty(avatar)){
            sysUser.setAvatar(avatar);
        }

        String phone = getPara("phone");
        if(StringUtils.notEmpty(phone)){
            sysUser.setPhone(phone);
        }

        String email = getPara("email");
        if(StringUtils.notEmpty(email)){
            sysUser.setEmail(email);
        }

        String name = getPara("name");
        if(StringUtils.notEmpty(name)){
            sysUser.setName(name);
        }

        String job = getPara("job");
        if(StringUtils.notEmpty(job)){
            sysUser.setJob(job);
        }

        String gender = getPara("gender");
        if(StringUtils.notEmpty(gender)){
            sysUser.setGender(gender);
        }

        if (sysUser.update()) {
            SysUser curUser = WebUtils.getSysUser(this);
            curUser.setName(sysUser.getName());
            curUser.setEmail(sysUser.getEmail());
            curUser.setPhone(sysUser.getPhone());
            curUser.setAvatar(sysUser.getAvatar());
            renderSuccess("信息修改成功");
        } else {
            renderFail("信息修改失败");
        }
    }
}
