package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.base.BaseSysRoleIncode;

/**
 * Generated model
 * DB: SYS_ROLE_INCODE  代码中硬编码的角色
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysRoleIncode extends BaseSysRoleIncode<SysRoleIncode> {
    public static final SysRoleIncode dao = new SysRoleIncode().dao();

    /**
     * 分页查询
     * @param pageNumber
     * @param pageSize
     * @param where
     * @return
     */
    public Page<SysRoleIncode> page(int pageNumber, int pageSize, String where){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from sys_role_incode  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }
}
