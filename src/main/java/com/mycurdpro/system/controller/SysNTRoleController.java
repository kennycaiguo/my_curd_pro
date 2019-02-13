package com.mycurdpro.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.SearchSql;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.utils.WebUtils;
import com.mycurdpro.system.model.SysNoticeTypeSysRole;

import java.util.Date;


/***
 * 通知类型 角色 关系
 */
public class SysNTRoleController extends BaseController {

    /**
     * 列表页
     */
    public void index() {
        render("system/sysNTRole.ftl");
    }

    /**
     * 通知类型 角色 datagrid 数据
     */
    @Before(SearchSql.class)
    public void query() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysNoticeTypeSysRole> sysNTRolePage = SysNoticeTypeSysRole.dao.page(pageNumber, pageSize, where);
        renderDatagrid(sysNTRolePage);
    }

    /**
     * 增加 关联关系
     */
    @Before(Tx.class)
    public void addAction() {
        String roleIds = getPara("roleIds");
        String sysNoticeTypeId = getPara("sysNoticeTypeId");
        SysNoticeTypeSysRole sysNTRoleOld;
        for (String roleId : roleIds.split(",")) {
            sysNTRoleOld = SysNoticeTypeSysRole.dao.findByIds(sysNoticeTypeId, roleId);
            if (sysNTRoleOld != null) {
                continue;
            }
            SysNoticeTypeSysRole sysNTRole = new SysNoticeTypeSysRole();
            sysNTRole.setSysNoticeTypeId(sysNoticeTypeId)
                    .setSysRoleId(roleId)
                    .setCreater(WebUtils.getSessionUsername(this))
                    .setCreateTime(new Date())
                    .save();
        }
        renderSuccess("关联角色成功");
    }

    /**
     * 单条 删除  关联关系
     */
    @Before(Tx.class)
    public void deleteAction() {
        // ,; 格式
        String idPairs = getPara("idPairs");
        if (StringUtils.isEmpty(idPairs)) {
            renderFail("参数不可为空");
            return;
        }
        String[] idPairAry = idPairs.split(";");
        String[] idAry;
        for (String idPair : idPairAry) {
            idAry = idPair.split(",");
            SysNoticeTypeSysRole.dao.deleteByIds(idAry[0], idAry[1]);
        }
        renderSuccess(Constant.DELETE_SUCCESS);
    }
}
