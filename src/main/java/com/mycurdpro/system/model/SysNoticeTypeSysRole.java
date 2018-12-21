package com.mycurdpro.system.model;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.system.model.base.BaseSysNoticeTypeSysRole;

/**
 * Generated model
 * DB: SYS_NOTICE_TYPE_SYS_ROLE  系统通知类型角色中间表
 *
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysNoticeTypeSysRole extends BaseSysNoticeTypeSysRole<SysNoticeTypeSysRole> {
    public static final SysNoticeTypeSysRole dao = new SysNoticeTypeSysRole().dao();


    /**
     * 分页查询
     * @param pageNumber
     * @param pageSize
     * @param where
     * @return
     */
    public Page<SysNoticeTypeSysRole> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select a.*,c.cate,c.name,c.code,  b.name as role_name,b.code as role_code, b.description as role_desc ";
        String sqlExceptSelect = " from sys_notice_type_sys_role a " +
                " left join sys_role b on a.sys_role_id = b.id " +
                " left join sys_notice_type c on a.sys_notice_type_id = c.id ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        sqlExceptSelect += " order by b.sort ";
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }
}
