package com.mycurdpro.system.controller;

import com.google.common.base.Objects;
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
import com.mycurdpro.common.validator.IdsRequired;
import com.mycurdpro.system.model.SysMenu;
import com.mycurdpro.system.model.SysRole;
import com.mycurdpro.system.model.SysRoleMenu;
import com.mycurdpro.system.model.SysUserRole;

import java.util.*;

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
        Page<SysUserRole> sysUserRolePage = SysUserRole.dao.pageWithUserInfo(pageNumber, pageSize, where);
        renderDatagrid(sysUserRolePage);
    }

    /**
     * 角色相关用户 删除
     */
    public void deleteUserRole() {
        String userId = getPara("userId");
        String roleId = getPara("roleId");
        if (StringUtils.isEmpty(roleId) || StringUtils.isEmpty(userId)) {
            renderFail("userId roleId 参数不可为空");
            return;
        }
        SysUserRole.dao.deleteById(userId, roleId);
        renderSuccess("角色用户删除成功");
    }


    /**
     * 角色配置菜单
     */
    public void newRoleMenu() {
        setAttr("roleId", getPara("id"));
        render("system/sysRole_menu.ftl");
    }

    /**
     * 角色配置菜单 数据
     */
    public void menuTreeChecked() {
        String id = getPara("roleId");
        // 角色相关菜单
        List<SysRoleMenu> sysRoleMenus = SysRoleMenu.dao.findByRoleId(id);
        // 全部菜单
        List<SysMenu> sysMenus = SysMenu.dao.findAll();
        List<Map<String, Object>> maps = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", sysMenu.getId());
            map.put("pid", sysMenu.getPid());
            map.put("text", sysMenu.getName());
            map.put("iconCls", sysMenu.getIcon());
            map.put("state", "open");
            for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
                // 中间表 有记录，且是 叶子 选中
                if (Objects.equal(sysRoleMenu.getSysMenuId(), sysMenu.getId())
                        && Objects.equal(sysMenu.getStr("IS_LEAF"), "1")) {
                    map.put("checked", true);
                    break;
                }
            }
            maps.add(map);
        }
        renderJson(maps);
    }

    /**
     * 角色配置菜单 更新
     */
    @Before(Tx.class)
    public void menuTreeUpdate() {
        String roleId = getPara("roleId");
        String menuIds = getPara("menuIds");
        if (StringUtils.isEmpty(roleId)) {
            renderFail("roleId 参数不可为空.");
            return;
        }
        // 删除 角色原有菜单
        String deleteSql = "delete from  sys_role_menu where sys_role_id = ?";
        Db.update(deleteSql, roleId);
        // 添加 角色新菜单
        if (StringUtils.notEmpty(menuIds)) {
            String[] menuIdAry = menuIds.split(",");
            for (String menuId : menuIdAry) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setSysRoleId(roleId).setSysMenuId(menuId)
                        .setCreater(WebUtils.getSessionUsername(this))
                        .setCreateTime(new Date())
                        .save();
            }
        }
        renderSuccess("配置菜单成功");
    }
}
