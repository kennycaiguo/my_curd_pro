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
import com.mycurdpro.system.model.SysRole;
import com.mycurdpro.system.model.SysUserRole;

import java.util.Date;

/**
 * 角色管理
 *
 * @author zhangchuang
 */
public class SysRoleController extends BaseController {


    /**
     * 首页
     */
    public void index() {
        render("system/sysRole.ftl");
    }


    /**
     * datagrid 数据
     */
    @SuppressWarnings("Duplicates")
    @Before(SearchSql.class)
    public void query() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysRole> sysRolePage = SysRole.dao.page(pageNumber, pageSize, where);
        renderDatagrid(sysRolePage);
    }


    /**
     * 新增 或 修改弹窗
     */
    public void newModel() {
        String id = getPara("id");
        if (StringUtils.notEmpty(id)) {
            SysRole sysRole = SysRole.dao.findById(id);
            setAttr("sysRole", sysRole);
        }
        render("system/sysRole_form.ftl");
    }


    /**
     * 新增 action
     */
    public void addAction() {
        SysRole sysRole = getBean(SysRole.class, "");
        sysRole.setId(IdUtils.id())
                .setCreater(WebUtils.getSessionUsername(this))
                .setCreateTime(new Date());
        if (sysRole.save()) {
            renderSuccess(Constant.ADD_SUCCESS);
        } else {
            renderFail(Constant.ADD_FAIL);
        }
    }

    /**
     * 修改 action
     */
    public void updateAction() {
        SysRole sysRole = getBean(SysRole.class, "");
        sysRole.setUpdater(WebUtils.getSessionUsername(this))
                .setUpdateTime(new Date());
        if (sysRole.update()) {
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
        Db.tx(() -> {
            // 删除角色数据
            String sql = "delete from sys_role where id in ('" + ids + "')";
            Db.update(sql);
            // 删除 角色用户 中间表
            sql = "delete from sys_user_role where sys_role_id in ('" + ids + "')";
            Db.update(sql);
            return true;
        });
        renderSuccess(Constant.DELETE_SUCCESS);
    }

    /**
     * 角色相关用户
     */
    public void openRoleUser() {
        setAttr("roleId", getPara("id"));
        render("system/sysRole_user.ftl");
    }
    /**
     * 角色相关用户数据
     */
    @Before(SearchSql.class)
    public void queryRoleUser() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysUserRole> authUserRolePage = SysUserRole.dao.pageWithUserInfo(pageNumber, pageSize, where);
        renderDatagrid(authUserRolePage);
    }
    /**
     * 角色相关用户 删除
     */
    public void deleteUserRole(){
        String userId = getPara("userId");
        String roleId = getPara("roleId");
        if(StringUtils.isEmpty(roleId) || StringUtils.isEmpty(userId)){
            renderFail("userId roleId 参数不可为空");
            return;
        }
        SysUserRole.dao.deleteById(userId,roleId);
        renderSuccess("角色用户删除成功");
    }
}
