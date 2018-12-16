package com.mycurdpro.system.model;

import com.mycurdpro.system.model.base.BaseSysRoleMenu;

import java.util.List;

/**
 * Generated model
 * DB: SYS_ROLE_MENU  角色菜单中间表
 *
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysRoleMenu extends BaseSysRoleMenu<SysRoleMenu> {
    public static final SysRoleMenu dao = new SysRoleMenu().dao();

    /**
     * 根据角色id查询
     * @param roleId
     * @return
     */
    public List<SysRoleMenu> findByRoleId(String roleId){
        String sql = "select sys_menu_id from sys_role_menu where sys_role_id = ?";
        return find(sql,roleId);
    }
}
