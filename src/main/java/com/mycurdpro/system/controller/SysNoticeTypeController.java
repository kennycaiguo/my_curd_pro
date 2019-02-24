package com.mycurdpro.system.controller;

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
import com.mycurdpro.system.model.SysNoticeType;

import java.util.Date;

public class SysNoticeTypeController extends BaseController {
    public void index() {
        render("system/sysNoticeType.ftl");
    }


    @Before(SearchSql.class)
    public void query() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysNoticeType> sysNoticeTypePage = SysNoticeType.dao.page(pageNumber, pageSize, where);
        renderDatagrid(sysNoticeTypePage);
    }

    public void newModel() {
        String id = getPara("id");
        if (StringUtils.notEmpty(id)) {
            SysNoticeType sysNoticeType = SysNoticeType.dao.findById(id);
            setAttr("sysNoticeType", sysNoticeType);
        }
        render("system/sysNoticeType_form.ftl");
    }

    public void addAction() {
        SysNoticeType sysNoticeType = getBean(SysNoticeType.class, "");
        sysNoticeType.setId(IdUtils.id());
        sysNoticeType.setCreater(WebUtils.getSessionUsername(this)).setCreateTime(new Date());
        if (sysNoticeType.save()) {
            renderSuccess(Constant.ADD_SUCCESS);
        } else {
            renderFail(Constant.ADD_FAIL);
        }
    }


    public void updateAction() {
        SysNoticeType sysNoticeType = getBean(SysNoticeType.class, "");
        sysNoticeType.setUpdater(WebUtils.getSessionUsername(this)).setUpdateTime(new Date());
        if (sysNoticeType.update()) {
            renderSuccess(Constant.UPDATE_SUCCESS);
        } else {
            renderFail(Constant.UPDATE_FAIL);
        }
    }


    @Before(IdsRequired.class)
    public void deleteAction() {
        String ids = getPara("ids").replaceAll(",", "','");
        Db.tx(() -> {
            String sql = "delete from sys_notice_type where id in ('" + ids + "')";
            Db.update(sql);
            sql = "delete from sys_notice_type_sys_role where sys_notice_type_id in ('" + ids + "')";
            Db.update(sql);
            sql = "delete from sys_notice_type_sys_user where sys_notice_type_id in ('" + ids + "')";
            Db.update(sql);
            // 删除通知消息表
            sql = "delete from sys_notice_detail where sys_notice_id in (select id from sys_notice where type_code in (select code from sys_notice_type where id in ('"+ids+"')))";
            Db.update(sql);
            sql = "delete from sys_notice where type_code in (select code from sys_notice_type where id in ('"+ids+"'))";
            Db.update(sql);

            return true;
        });
        renderSuccess(Constant.DELETE_SUCCESS);
    }
}
