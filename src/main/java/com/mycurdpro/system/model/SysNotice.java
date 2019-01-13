package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.base.BaseSysNotice;

/**
 * Generated model
 * DB: SYS_NOTICE  通知消息
 *
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysNotice extends BaseSysNotice<SysNotice> {
    public static final SysNotice dao = new SysNotice().dao();


    /**
     * 分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param where
     * @return
     */
    public Page<SysNotice> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " SELECT a.id,a.title,a.content,a.create_time, c.id as detail_id,c.has_read,b.logo ";
        String sqlExceptSelect = " FROM sys_notice a " +
                "  left join sys_notice_type b on a.type_code= b.code " +
                "  left join sys_notice_detail c on c.sys_notice_id = a.id " +
                "  left join sys_user d on c.receiver = d.id ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }

        sqlExceptSelect += " order by a.create_time desc ";
        return paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }

}
