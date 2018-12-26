package com.mycurdpro.system;


import com.jfinal.config.Routes;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.system.controller.*;

/**
 * System 模块路由配置
 *
 * @author zhangchuang
 */
public class SystemRoute extends Routes {

    @Override
    public void config() {
        // 数据字典
        add("/sysDict", SysDictController.class, Constant.VIEW_PATH);
        // 访问日志
        add("/sysVisitLog", SysVisitLogController.class, Constant.VIEW_PATH);

        // 角色管理
        add("/sysRole", SysRoleController.class, Constant.VIEW_PATH);

        // 机构管理
        add("/sysOrg", SysOrgController.class, Constant.VIEW_PATH);

        // 菜单管理
        add("/sysMenu", SysMenuController.class, Constant.VIEW_PATH);

        // 用户管理
        add("/sysUser", SysUserController.class, Constant.VIEW_PATH);

        // 业务日志
        add("/sysServiceLog",SysServiceLogController.class,Constant.VIEW_PATH);

        // 用户解锁
        add("/sysUserUnlock",SysUserUnlockController.class,Constant.VIEW_PATH);

        // 地区数据
        add("/sysDataRegion",SysDataRegionController.class,Constant.VIEW_PATH);

        // 用户文件
        add("/sysFile",SysFileController.class,Constant.VIEW_PATH);


        // 通知类型
        add("/sysNoticeType",SysNoticeTypeController.class,Constant.VIEW_PATH);

        // 通知类型关联 用户
        add("/sysNTUser",SysNTUserController.class,Constant.VIEW_PATH);

        // 通知类型关联 角色
        add("/sysNTRole",SysNTRoleController.class,Constant.VIEW_PATH);

        // 个人笔记
        add("/sysNote",SysNoteController.class,Constant.VIEW_PATH);
    }
}
