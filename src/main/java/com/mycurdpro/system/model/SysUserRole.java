package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
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
     * 通过用户id 查询 角色id
     *
     * @param userId
     * @return
     */
    public String findRoleIdsByUserId(String userId) {
        String sql = "select WM_CONCAT(c.ID) as ROLE_IDS" +
                "  from sys_user a, sys_user_role b,sys_role c " +
                "  where a.id = b.sys_user_id and b.sys_role_id = c.id  and a.id = ? ";
        Record record = Db.findFirst(sql, userId);
        return record.getStr("role_IDS");
    }


    /**
     * 通过用户查询角色编码
     *
     * @param userId
     * @return
     */
    public String findRoleCodesByUserId(String userId) {
        String sql = "select WM_CONCAT(c.CODE) as ROLE_CODES" +
                "  from sys_user a, sys_user_role b,sys_role c " +
                "  where a.id = b.sys_user_id and b.sys_role_id = c.id  and a.id = ? ";
        Record record = Db.findFirst(sql, userId);
        return record.getStr("ROLE_CODES");
    }


    /**
     * 分页查询, 角色数据
     *
     * @param pageNumber
     * @param pageSize
     * @param where
     * @return
     */
    public Page<SysUserRole> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select a.SYS_ROLE_ID,a.SYS_USER_ID,b.NAME, b.CODE, b.DESCRIPTION, a.CREATER,a.CREATE_TIME ";
        String sqlExceptSelect = " from sys_user_role a, sys_role b  where a.sys_role_id = b.id ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " and " + where;
        }
        sqlExceptSelect += " order by b.sort asc ";
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }

    /**
     * 分页查询, 用户数据
     *
     * @param pageNumber
     * @param pageSize
     * @param where
     * @return
     */
    public Page<SysUserRole> pageWithUserInfo(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select a.SYS_ROLE_ID,a.SYS_USER_ID,a.CREATER,a.CREATE_TIME, b.USERNAME,b.NAME,b.JOB ";
        String sqlExceptSelect = " from sys_user_role a, sys_user b  where a.sys_user_id = b.id ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " and " + where;
        }
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }


}
