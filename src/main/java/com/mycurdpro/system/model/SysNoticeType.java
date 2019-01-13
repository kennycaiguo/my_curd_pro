package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.base.BaseSysNoticeType;

/**
 * Generated model
 * DB: SYS_NOTICE_TYPE  通知分类
 *
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysNoticeType extends BaseSysNoticeType<SysNoticeType> {
    public static final SysNoticeType dao = new SysNoticeType().dao();


    /**
     * 分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param where
     * @return
     */
    public Page<SysNoticeType> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from sys_notice_type  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }


    /**
     * 根据编码查询
     *
     * @param code
     * @return
     */
    public SysNoticeType findByCode(String code) {
        return findFirst("select * from sys_notice_type where code = ? ", code);
    }

}
