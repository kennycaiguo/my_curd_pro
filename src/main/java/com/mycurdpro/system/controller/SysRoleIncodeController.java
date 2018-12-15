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
import com.mycurdpro.system.model.SysRoleIncode;

import java.util.Date;

/**
 * 固化角色
 *
 * @author zhangchuang
 */
public class SysRoleIncodeController extends BaseController {
    public void index() {
        render("system/sysRoleIncode.ftl");
    }


    @Before(SearchSql.class)
    public void query() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysRoleIncode> sysRoleIncodePage = SysRoleIncode.dao.page(pageNumber, pageSize, where);
        renderDatagrid(sysRoleIncodePage);
    }

    public void newModel() {
        String id = getPara("id");
        if (StringUtils.notEmpty(id)) {
            SysRoleIncode sysRoleIncode = SysRoleIncode.dao.findById(id);
            setAttr("sysRoleIncode", sysRoleIncode);
        }
        render("system/sysRoleIncode_form.ftl");
    }

    public void addAction() {
        SysRoleIncode sysRoleIncode = getBean(SysRoleIncode.class, "");
        sysRoleIncode.setId(IdUtils.id()).setCreater(WebUtils.getSessionUsername(this)).setCreateTime(new Date());
        if (sysRoleIncode.save()) {
            renderSuccess(Constant.ADD_SUCCESS);
        } else {
            renderFail(Constant.ADD_FAIL);
        }
    }

    public void updateAction() {
        SysRoleIncode sysRoleIncode = getBean(SysRoleIncode.class, "");
        sysRoleIncode.setUpdater(WebUtils.getSessionUsername(this)).setUpdateTime(new Date());
        if (sysRoleIncode.update()) {
            renderSuccess(Constant.UPDATE_SUCCESS);
        } else {
            renderFail(Constant.UPDATE_FAIL);
        }
    }

    @Before(IdsRequired.class)
    public void deleteAction() {
        String ids = getPara("ids").replaceAll(",", "','");
        Db.tx(() -> {
            String sql = "delete from sys_role_incode where id in ('" + ids + "')";
            Db.update(sql);
            sql = "delete from sys_user_roleincode where sys_roleincode_id in ('" + ids + "')";
            Db.update(sql);
            return true;
        });
        renderSuccess(Constant.DELETE_SUCCESS);
    }
}
