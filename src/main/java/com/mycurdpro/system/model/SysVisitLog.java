package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.base.BaseSysVisitLog;

/**
 * Generated model
 * DB: SYS_VISIT_LOG  系统访问日志
 *
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysVisitLog extends BaseSysVisitLog<SysVisitLog> {
    public static final SysVisitLog dao = new SysVisitLog().dao();


    /**
     * 分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param where
     * @return
     */
    public Page<SysVisitLog> page(int pageNumber, int pageSize, String where) {
        // 排除 参数 和 异常
        String sqlSelect = " select id,sys_user,sys_user_ip,url,create_time,type, DECODE(ERROR, null,0,1) as ERROR_FLAG ";
        String sqlExceptSelect = " from sys_visit_log  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        sqlExceptSelect += " order by create_time desc ";
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }
}
