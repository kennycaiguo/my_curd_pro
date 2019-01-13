package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.base.BaseSysUser;

/**
 * Generated model
 * DB: SYS_USER  系统用户表
 *
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysUser extends BaseSysUser<SysUser> {
    public static final SysUser dao = new SysUser().dao();


    /**
     * 根据用户名和 密码查询
     *
     * @param username
     * @param password
     * @return
     */
    public SysUser findByUsernameAndPassword(String username, String password) {
        String sql = "select * from sys_user where username = ? and password = ?";
        return findFirst(sql, username, password);
    }


    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    public SysUser findByUsername(String username) {
        String sql = "select * from sys_user where username= ? ";
        return findFirst(sql, username);
    }


    /**
     * 分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param where
     * @return
     */
    public Page<SysUser> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select a.*, b.name as ORG_NAME, c.dict_label as GENDER_TEXT,d.dict_label as JOB_LEVEL_TEXT, e.dict_label as USER_STATE_TEXT ";
        String sqlExceptSelect = " from sys_user a " +
                " left join sys_org b on a.org_id = b.id  " +
                " left join sys_dict c on c.group_code='gender' and a.gender = c.dict_value" +
                " left join sys_dict d on d.group_code='jobLevel' and a.job_level = d.dict_value " +
                " left join sys_dict e on e.group_code='userState' and a.user_state= e.dict_value";
        sqlExceptSelect += " where a.del_flag is  null ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " and " + where;
        }
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }

    /**
     * 根据用户名查询用户完整信息
     *
     * @param username
     * @return
     */
    public SysUser findInfoByUsername(String username) {
        String sqlSelect = " select a.*, b.name as ORG_NAME,d.dict_label as JOB_LEVEL_TEXT, e.dict_label as USER_STATE_TEXT ";
        String sqlExceptSelect = " from sys_user a " +
                " left join sys_org b on a.org_id = b.id  " +
                " left join sys_dict d on d.group_code='jobLevel' and a.job_level = d.dict_value " +
                " left join sys_dict e on e.group_code='userState' and a.user_state= e.dict_value";
        sqlExceptSelect += " where a.username = ? ";
        return this.findFirst(sqlSelect + sqlExceptSelect, username);
    }
}



