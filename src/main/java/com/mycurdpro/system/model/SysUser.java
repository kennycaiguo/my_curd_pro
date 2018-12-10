package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.base.BaseSysUser;

/**
 * Generated model
 * DB: SYS_USER  系统用户表
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysUser extends BaseSysUser<SysUser> {
    public static final SysUser dao = new SysUser().dao();


    /**
     * 分页查询
     * @param pageNumber
     * @param pageSize
     * @param where
     * @return
     */
    public Page<SysUser> page(int pageNumber, int pageSize, String where){
        String sqlSelect = " select a.*, b.name as ORG_NAME ";
        String sqlExceptSelect = " from sys_user a left join sys_org b on a.org_id = b.id  ";
        sqlExceptSelect+=" where a.del_flag is not null ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " and " + where;
        }
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }
}
