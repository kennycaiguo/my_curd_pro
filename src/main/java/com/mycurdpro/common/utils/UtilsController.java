package com.mycurdpro.common.utils;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.PermissionInterceptor;
import com.mycurdpro.common.interceptor.SearchSql;
import com.mycurdpro.system.model.SysOrg;
import com.mycurdpro.system.model.SysRole;
import com.mycurdpro.system.model.SysUser;

/**
 * 工具controller
 */
@Clear(PermissionInterceptor.class)
public class UtilsController extends BaseController {

    /**
     * 系统用户信息
     */
    public void userInfo() {
        String username = getPara("username");
        if (StringUtils.notEmpty(username)) {
            SysUser sysUser = SysUser.dao.findInfoByUsername(username);
            setAttr("sysUser", sysUser);
        }
        setAttr("username", username);

        render("common/utils/userInfo.ftl");
    }

    /**
     * 组织机构信息
     */
    public void orgInfo() {
        String orgId = getPara("id");
        if (StringUtils.notEmpty(orgId)) {
            SysOrg sysOrg = SysOrg.dao.findById(orgId);
            setAttr("sysOrg", sysOrg);
        }
        setAttr("orgId", orgId);
        render("common/utils/orgInfo.ftl");
    }

    /**
     * 角色选择
     */
    public void role() {
        // layer iframe id  和 window.name 一致
        setAttr("iframeId", getPara("iframeId"));
        String singleSelect = getPara("singleSelect", "false");
        setAttr("singleSelect", singleSelect);
        setAttr("yesBtnTxt", getPara("yesBtnTxt", "添加角色"));
        render("common/utils/role.ftl");
    }

    /**
     * 角色选择 数据
     */
    @SuppressWarnings("Duplicates")
    @Before(SearchSql.class)
    public void queryRole() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysRole> sysRolePage = SysRole.dao.page(pageNumber, pageSize, where);
        renderDatagrid(sysRolePage);
    }

}
