package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.base.BaseSysTaskLog;

/**
 * Generated model
 * DB: SYS_TASK_LOG  定时任务日志
 *
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysTaskLog extends BaseSysTaskLog<SysTaskLog> {
    public static final SysTaskLog dao = new SysTaskLog().dao();


    /**
     * 分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param where
     * @return
     */
    public Page<SysTaskLog> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from sys_task_log  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        sqlExceptSelect += " order by id desc ";
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }
}
