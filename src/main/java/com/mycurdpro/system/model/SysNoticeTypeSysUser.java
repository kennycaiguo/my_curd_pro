package com.mycurdpro.system.model;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.system.model.base.BaseSysNoticeTypeSysUser;

/**
 * Generated model
 * DB: SYS_NOTICE_TYPE_SYS_USER  通知类型系统用户中间表
 *
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysNoticeTypeSysUser extends BaseSysNoticeTypeSysUser<SysNoticeTypeSysUser> {
    public static final SysNoticeTypeSysUser dao = new SysNoticeTypeSysUser().dao();


    /**
     * 分页查询
     * @param pageNumber
     * @param pageSize
     * @param where
     * @return
     */
    public Page<SysNoticeTypeSysUser> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " SELECT  a.*,b.name,b.code,b.cate, c.username   ";
        String sqlExceptSelect = " FROM sys_notice_type_sys_user a " +
                " LEFT JOIN sys_notice_type b ON a.sys_notice_type_id = b.id  " +
                " LEFT JOIN sys_user c ON a.sys_user_id = c.id ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }
}
