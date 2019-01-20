package com.mycurdpro.example.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.SearchSql;
import com.mycurdpro.common.utils.Id.IdUtils;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.utils.WebUtils;
import com.mycurdpro.common.validator.IdRequired;
import com.mycurdpro.common.validator.IdsRequired;
import com.mycurdpro.example.model.ExMainTable;
import com.mycurdpro.example.model.ExSonTable1;
import com.mycurdpro.example.model.ExSonTable2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * EX_MAIN_TABLE 控制器
 *
 * @author zhangchuang
 * @date 2019-01-17 19:04:42
 */
public class ExMainTableController extends BaseController {

    private final static Logger LOG = LoggerFactory.getLogger(ExMainTableController.class);

    /**
     * 列表页
     */
    public void index() {
        render("example/exMainTable.ftl");
    }

    /**
     * 列表数据
     */
    @Before(SearchSql.class)
    public void query() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<ExMainTable> exMainTablePage = ExMainTable.dao.page(pageNumber, pageSize, where);
        renderDatagrid(exMainTablePage);
    }


    /**
     * 打开新增或者修改弹出框
     */
    public void newModel() {
        String id = getPara("id");
        if (StringUtils.notEmpty(id)) {
            ExMainTable exMainTable = ExMainTable.dao.findById(id);
            setAttr("exMainTable", exMainTable);

            List<ExSonTable1> exSonTable1s = ExSonTable1.dao.find("select * from EX_SON_TABLE1 where MAIN_ID = ? order by ID ", id);
            setAttr("exSonTable1s", exSonTable1s);
            List<ExSonTable2> exSonTable2s = ExSonTable2.dao.find("select * from EX_SON_TABLE2 where MAIN_ID = ? order by ID ", id);
            setAttr("exSonTable2s", exSonTable2s);
        }
        render("example/exMainTable_form.ftl");
    }


    /**
     * 新增 action
     */
    @Before(Tx.class)
    public void addAction() {
        ExMainTable exMainTable = getBean(ExMainTable.class, "exMainTable");
        exMainTable.setId(IdUtils.id())
                .setCreater(WebUtils.getSessionUsername(this))
                .setCreateTime(new Date());
        exMainTable.save();

        // 保存子表
        List<ExSonTable1> exSonTable1s = getBeans(ExSonTable1.class, "exSonTable1");
        exSonTable1s.forEach(item ->
                item.setId(IdUtils.id()).setMainId(exMainTable.getId())
                        .setCreateTime(new Date())
                        .setCreater(WebUtils.getSessionUsername(this)));
        Db.batchSave(exSonTable1s, exSonTable1s.size());

        List<ExSonTable2> exSonTable2s = getBeans(ExSonTable2.class, "exSonTable2");
        exSonTable2s.forEach(item ->
                item.setId(IdUtils.id()).setMainId(exMainTable.getId())
                        .setCreateTime(new Date())
                        .setCreater(WebUtils.getSessionUsername(this)));
        Db.batchSave(exSonTable2s, exSonTable2s.size());

        renderSuccess(Constant.ADD_SUCCESS);
    }

    /**
     * 修改 action
     */
    @SuppressWarnings("Duplicates")
    @Before(Tx.class)
    public void updateAction() {
        ExMainTable exMainTable = getBean(ExMainTable.class, "exMainTable");
        exMainTable.setUpdater(WebUtils.getSessionUsername(this))
                .setUpdateTime(new Date());
        exMainTable.update();

        // 保存子表
        List<ExSonTable1> exSonTable1s = getBeans(ExSonTable1.class, "exSonTable1");
        exSonTable1s.forEach(item -> {
            item.setMainId(exMainTable.getId());
            if (StringUtils.isEmpty(item.getId())) {
                item.setId(IdUtils.id()).setCreateTime(new Date())
                        .setCreater(WebUtils.getSessionUsername(this)).save();
            } else {
                item.setUpdateTime(new Date())
                        .setUpdater(WebUtils.getSessionUsername(this)).update();
            }
        });

        List<ExSonTable2> exSonTable2s = getBeans(ExSonTable2.class, "exSonTable2");
        exSonTable2s.forEach(item -> {
            item.setMainId(exMainTable.getId());
            if (StringUtils.isEmpty(item.getId())) {
                item.setId(IdUtils.id()).setCreateTime(new Date())
                        .setCreater(WebUtils.getSessionUsername(this)).save();
            } else {
                item.setUpdateTime(new Date())
                        .setUpdater(WebUtils.getSessionUsername(this)).update();
            }
        });
        renderSuccess(Constant.UPDATE_SUCCESS);
    }


    /**
     * 删除 action
     */
    @Before({IdsRequired.class, Tx.class})
    public void deleteAction() {
        String ids = getPara("ids").replaceAll(",", "','");

        String deleteSql;
        // 删从表
        deleteSql = "delete from EX_SON_TABLE1 where MAIN_ID in ('" + ids + "')";
        Db.update(deleteSql);
        deleteSql = "delete from EX_SON_TABLE1 where MAIN_ID in ('" + ids + "')";
        Db.update(deleteSql);

        // 删主表
        deleteSql = "delete from EX_MAIN_TABLE where id in ( '" + ids + "' ) ";
        Db.update(deleteSql);

        renderSuccess(Constant.DELETE_SUCCESS);
    }


    /**
     * 删子表1 数据
     */
    @Before(IdRequired.class)
    public void deleteExSonTable1Action() {
        String id = getPara("id");
        ExSonTable1.dao.deleteById(id);
        renderSuccess(Constant.DELETE_SUCCESS);
    }


    /**
     * 删子表2 数据
     */
    @Before(IdRequired.class)
    public void deleteExSonTable2Action() {
        String id = getPara("id");
        ExSonTable2.dao.deleteById(id);

        renderSuccess(Constant.DELETE_SUCCESS);
    }

}
