package com.mycurdpro.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.SearchSql;
import com.mycurdpro.common.validator.IdsRequired;
import com.mycurdpro.system.model.SysServiceLog;

/**
 * 业务日志
 */
public class SysServiceLogController extends BaseController {
    public void index(){
        render("system/sysServiceLog.ftl");
    }


    @Before(SearchSql.class)
    public void query(){
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysServiceLog> sysServiceLogPage = SysServiceLog.dao.page(pageNumber, pageSize, where);
        renderDatagrid(sysServiceLogPage);
    }


    @Before(IdsRequired.class)
    public void deleteAction(){
        String ids = getPara("ids").replaceAll(",", "','");
        String sql = "delete from sys_service_log where  id in ('" + ids + "')";
        Db.update(sql);
        renderSuccess(Constant.DELETE_SUCCESS);
    }
}
