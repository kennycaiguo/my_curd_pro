package com.mycurdpro;

import com.jfinal.aop.Clear;
import com.jfinal.aop.Duang;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.interceptor.PermissionInterceptor;
import com.mycurdpro.common.utils.WebUtils;
import com.mycurdpro.common.utils.ws.UserIdEncryptUtils;
import com.mycurdpro.system.model.SysMenu;
import com.mycurdpro.system.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
}
