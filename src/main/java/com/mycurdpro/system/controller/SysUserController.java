package com.mycurdpro.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.kit.HashKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.SearchSql;
import com.mycurdpro.common.utils.Id.IdUtils;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.utils.WebUtils;
import com.mycurdpro.common.validator.IdRequired;
import com.mycurdpro.common.validator.IdsRequired;
import com.mycurdpro.system.model.SysUser;

import java.util.Date;

public class SysUserController extends BaseController {

    /**
     * 主页面
     */
    public void index(){
        render("system/sysUser.ftl");
    }


    /**
     * datagrid 数据
     */
    @Before(SearchSql.class)
    public void query(){
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysUser> sysUserPage = SysUser.dao.page(pageNumber,pageSize,where);
        renderDatagrid(sysUserPage);
    }

    /**
     * 新增或者修改弹窗
     */
    public void newModel(){
        String id = getPara("id");
        if(StringUtils.notEmpty(id)){
            SysUser sysUser = SysUser.dao.findById(id);
            setAttr("sysUser",sysUser);
        }
        render("system/sysUser_form.ftl");
    }


    /**
     * add
     */
    public void addAction(){
        SysUser sysUser = getBean(SysUser.class,"");
        sysUser.setId(IdUtils.id()).setCreater(WebUtils.getSessionUsername(this)).setCreateTime(new Date());
        sysUser.setPassword(HashKit.sha1(sysUser.getPassword()));

        if(sysUser.save()){
            renderSuccess(Constant.ADD_SUCCESS);
        }else{
            renderFail(Constant.ADD_FAIL);
        }

    }

    /**
     * update
     */
    public void updateAction(){
        SysUser sysUser = getBean(SysUser.class,"");
        sysUser.setUpdater(WebUtils.getSessionUsername(this)).setUpdateTime(new Date());
        if(sysUser.update()){
            renderSuccess(Constant.UPDATE_SUCCESS);
        }else{
            renderFail(Constant.UPDATE_FAIL);
        }
    }

    /**
     * delete
     */
    @Before(IdsRequired.class)
    public void deleteAction(){
        String ids = getPara("ids").replaceAll(",","','");
        Db.tx(() -> {
            // 修改删除标志
            String sql = "update sys_user set del_flag = 'x' where id in ('"+ids+"')";
            Db.update(sql);
            return true;
        });
        renderSuccess(Constant.DELETE_SUCCESS);
    }



    /**
     * 修改密码页面
     */
    @Before(IdRequired.class)
    public void goChangePwd(){
        setAttr("id",getPara("id"));
        render("system/sysUser_changePwd.ftl");
    }


    /**
     * 修改密码
     */
    public void changePwdAction(){
        String id = getPara("id");
        String pwd = getPara("pwd");
        String rePwd = getPara("rePwd");
        if(StringUtils.isEmpty(id) || StringUtils.isEmpty(pwd) || StringUtils.isEmpty(rePwd)){
            renderFail("参数不可为空");
            return;
        }
        SysUser sysUser = SysUser.dao.findById(id);
        if(sysUser==null){
            renderFail("id 参数错误");
            return;
        }
        if(!pwd.equals(rePwd)){
            renderFail("两次密码不一致");
            return;
        }

        sysUser.setPassword(HashKit.sha1(pwd));
        if(sysUser.update()){
            renderSuccess("重置密码成功");
        }else{
            renderFail("重置密码失败");
        }
    }
}
