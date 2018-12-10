package com.mycurdpro.system;


import com.jfinal.config.Routes;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.VisitLogInterceptor;
import com.mycurdpro.system.controller.*;
import com.mycurdpro.system.model.SysUser;

/**
 * System 模块路由配置
 * @author zhangchuang
 */
public class SystemRoute extends Routes {

    @Override
    public void config() {
        addInterceptor(new VisitLogInterceptor());
        // 数据字典
        add("/sysDict", SysDictController.class, Constant.VIEW_PATH);
        // 访问日志
        add("/sysVisitLog", SysVisitLogController.class,Constant.VIEW_PATH);

        // 角色管理
        add("/sysRole", SysRoleController.class,Constant.VIEW_PATH);

        // 机构管理
        add("/sysOrg", SysOrgController.class,Constant.VIEW_PATH);

        // 菜单管理
        add("/sysMenu", SysMenuController.class,Constant.VIEW_PATH);

        // 代码中的角色，用作按钮权限控制
        add("/sysRoleIncode", SysRoleIncodeController.class,Constant.VIEW_PATH);

        // 用户管理
        add("/sysUser", SysUserController.class,Constant.VIEW_PATH);
    }
}
