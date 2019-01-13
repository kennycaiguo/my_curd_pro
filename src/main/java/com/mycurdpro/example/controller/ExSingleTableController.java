package com.mycurdpro.example.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.SearchSql;
import com.mycurdpro.common.utils.Id.IdUtils;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.utils.WebUtils;
import com.mycurdpro.common.validator.IdsRequired;
import com.mycurdpro.example.model.ExSingleTable;

import java.util.Date;

/**
 * EX_SINGLE_TABLE 控制器
 *
 * @author zhangchuang
 * @date 2019-01-13 11:13:24
 */
public class ExSingleTableController extends BaseController {

    /**
     * 列表页
     */
    public void index() {
        render("example/exSingleTable.ftl");
    }

    /**
     * 列表数据
     */
    @Before(SearchSql.class)
    public void query() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<ExSingleTable> exSingleTablePage = ExSingleTable.dao.page(pageNumber, pageSize, where);
        renderDatagrid(exSingleTablePage);
    }


    /**
     * 打开新增或者修改弹出框
     */
    public void newModel() {
        String id = getPara("id");
        if (StringUtils.notEmpty(id)) {
            ExSingleTable exSingleTable = ExSingleTable.dao.findById(id);
            setAttr("exSingleTable", exSingleTable);
        }
        render("example/exSingleTable_form.ftl");
    }


    /**
     * 新增 action
     */
    public void addAction() {
        ExSingleTable exSingleTable = getBean(ExSingleTable.class, "");
        exSingleTable.setId(IdUtils.id())
                .setCreater(WebUtils.getSessionUsername(this))
                .setCreateTime(new Date());
        if (exSingleTable.save()) {
            renderSuccess(Constant.ADD_SUCCESS);
        } else {
            renderFail(Constant.ADD_FAIL);
        }
    }

    /**
     * 修改 action
     */
    public void updateAction() {
        ExSingleTable exSingleTable = getBean(ExSingleTable.class, "");
        exSingleTable.setUpdater(WebUtils.getSessionUsername(this))
                .setUpdateTime(new Date());
        if (exSingleTable.update()) {
            renderSuccess(Constant.UPDATE_SUCCESS);
        } else {
            renderFail(Constant.UPDATE_FAIL);
        }
    }

    /**
     * 删除 action
     */
    @Before(IdsRequired.class)
    public void deleteAction() {
        String ids = getPara("ids").replaceAll(",", "','");
        String deleteSql = "delete from  where id in ( '" + ids + "' ) ";
        Db.update(deleteSql);
        renderSuccess(Constant.DELETE_SUCCESS);
    }
}
