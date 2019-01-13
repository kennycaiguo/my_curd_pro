package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
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
     *
     * @param roleId
     * @return
     */
    public List<SysRoleMenu> findByRoleId(String roleId) {
        String sql = "select sys_menu_id from sys_role_menu where sys_role_id = ?";
        return find(sql, roleId);
    }

    /**
     * 分页查询, 角色数据
     *
     * @param pageNumber
     * @param pageSize
     * @param where
     * @return
     */
    public Page<SysRoleMenu> pageWithRoleInfo(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select a.SYS_ROLE_ID,a.SYS_MENU_ID,a.CREATER,a.CREATE_TIME, b.NAME,b.CODE ";
        String sqlExceptSelect = " from sys_role_menu a, sys_role b  where a.sys_role_id = b.id ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " and " + where;
        }
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }
}
