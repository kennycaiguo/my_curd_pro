package com.mycurdpro.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.SearchSql;
import com.mycurdpro.common.utils.WebUtils;
import com.mycurdpro.system.model.SysNoticeTypeSysUser;

import java.util.Date;

/**
 * 通知类型 用户 关系
 */
public class SysNTUserController extends BaseController {
    /**
     * 列表页
     */
    public void index() {
        render("system/sysNTUser.ftl");
    }


    /**
     * 通知类型 用户 datagrid 数据
     */
    @Before(SearchSql.class)
    public void query() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysNoticeTypeSysUser> sysNTUserPage = SysNoticeTypeSysUser.dao.page(pageNumber, pageSize, where);
        renderDatagrid(sysNTUserPage);
    }


    /**
     * 增加 关联关系
     */
    @Before(Tx.class)
    public void addAction() {
        String userIds = getPara("userIds");
        String sysNoticeTypeId = getPara("sysNoticeTypeId");
        SysNoticeTypeSysUser sysNTUserOld;
        for (String userId : userIds.split(",")) {
            sysNTUserOld = SysNoticeTypeSysUser.dao.findById(sysNoticeTypeId, userId);
            if (sysNTUserOld != null) {
                continue;
            }
            SysNoticeTypeSysUser sysNTUser = new SysNoticeTypeSysUser();
            sysNTUser.setSysNoticeTypeId(sysNoticeTypeId)
                    .setSysUserId(userId)
                    .setCreater(WebUtils.getSessionUsername(this))
                    .setCreateTime(new Date())
                    .save();
        }
        renderSuccess("关联用户成功");
    }

    /**
     * 单条 删除  关联关系
     */
    @Before(Tx.class)
    public void deleteAction() {
        String sysNoticeTypeId = getPara("sysNoticeTypeId");
        String userId = getPara("userId");
        if (SysNoticeTypeSysUser.dao.deleteById(sysNoticeTypeId, userId)) {
            renderSuccess(Constant.DELETE_SUCCESS);
        } else {
            renderFail(Constant.DELETE_FAIL);
        }
    }
}
