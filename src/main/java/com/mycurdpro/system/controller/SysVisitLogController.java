package com.mycurdpro.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.annotation.RequireRole;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.*;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.validator.IdsRequired;
import com.mycurdpro.system.model.SysVisitLog;

/**
 * 访问日志
 */
@Clear(ExceptionInterceptor.class)
public class SysVisitLogController extends BaseController {


    @Before(ControlDomByRole.class)
    public void index() {
        render("system/sysVisitLog.ftl");
    }


    /**
     * datagrid 数据
     */
    @Before(SearchSql.class)
    public void query() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysVisitLog> sysVisitLogPage = SysVisitLog.dao.page(pageNumber, pageSize, where);
        renderDatagrid(sysVisitLogPage);
    }


    /**
     * 批量删除
     */
    @Clear(PermissionInterceptor.class)
    @Before({RoleInterceptor.class,IdsRequired.class})
    @RequireRole(value = "admin,GOD",relation = RequireRole.Relation.AND) // 测试无权限
    public void deleteAction() {
        String ids = getPara("ids").replaceAll(",", "','");
        String sql = "delete from sys_visit_log where  id in ('" + ids + "')";
        Db.update(sql);
        renderSuccess(Constant.DELETE_SUCCESS);
    }


    /**
     * 查看按钮
     */
    public void view() {
        String id = getPara("id");
        if (StringUtils.notEmpty(id)) {
            SysVisitLog sysVisitLog = SysVisitLog.dao.findById(id);
            setAttr("sysVisitLog", sysVisitLog);
        }
        render("system/sysVisitLog_view.ftl");
    }
}
