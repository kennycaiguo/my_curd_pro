package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.base.BaseSysUserRole;

import java.util.List;

/**
 * Generated model
 * DB: SYS_USER_ROLE  用户角色中间表
 *
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysUserRole extends BaseSysUserRole<SysUserRole> {
    public static final SysUserRole dao = new SysUserRole().dao();

    /**
     * 根据 userid 查询
     *
     * @param userId
     * @return
     */
    public List<SysUserRole> findUserRolesByUserId(Long userId) {
        String sql = "select * from sys_user_role where sys_user_id = ? ";
        return find(sql, userId);
    }


    /**
     * 分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param where
     * @return
     */
    public Page<SysUserRole> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select b.NAME, b.CODE, b.DESCRIPTION, a.CREATER,a.CREATE_TIME ";
        String sqlExceptSelect = " from sys_user_role a, sys_role b  where a.sys_role_id = b.id ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " and " + where;
        }
        sqlExceptSelect += " order by b.sort asc ";
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }


}
