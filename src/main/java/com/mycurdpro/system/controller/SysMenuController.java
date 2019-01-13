package com.mycurdpro.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.PermissionInterceptor;
import com.mycurdpro.common.interceptor.SearchSql;
import com.mycurdpro.common.utils.Id.IdUtils;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.utils.WebUtils;
import com.mycurdpro.common.validator.IdRequired;
import com.mycurdpro.system.model.SysMenu;
import com.mycurdpro.system.model.SysRoleMenu;

import java.util.*;

/**
 * 菜单管理
 */
public class SysMenuController extends BaseController {

    public void index() {
        render("system/sysMenu.ftl");
    }

    /**
     * treegrid 查询数据
     */
    public void query() {
        // 初始传递pid 为 0
        String name = getPara("search_LIKE_NAME"); // 根据名查询
        List<SysMenu> sysMenus;
        if (StringUtils.isEmpty(name)) {
            // 查询所有
            sysMenus = SysMenu.dao.findAll();
        } else {
            // 根据名字查询
            String ids = SysMenu.dao.findIdsByName(name);
            if (StringUtils.notEmpty(ids)) {
                ids = ids.replaceAll(",", "','");
                sysMenus = SysMenu.dao.findInIds(ids);
            } else {
                sysMenus = new ArrayList<>();
            }
            for (SysMenu sysMenu : sysMenus) {
                if (sysMenu.getInt("IS_LEAF") == 0) {
                    sysMenu.put("state", "closed");
                }
            }
        }

        // easyui 图标
        for (SysMenu sysMenu : sysMenus) {
            sysMenu.put("iconCls", sysMenu.getIcon());
        }

        renderJson(sysMenus);
    }

    /**
     * 新增 或 修改 弹窗
     */
    public void newModel() {
        String id = getPara("id");
        if (StringUtils.notEmpty(id)) {
            // 编辑
            SysMenu sysMenu = SysMenu.dao.findById(id);
            setAttr("sysMenu", sysMenu);
            if (sysMenu != null) {
                setAttr("pid", sysMenu.getPid());
            }
        } else {
            // 新增
            setAttr("pid", getPara("pid", "0"));
        }
        render("system/sysMenu_form.ftl");
    }

    /**
     * 添加 action
     */
    public void addAction() {
        SysMenu sysMenu = getBean(SysMenu.class, "");
        // 地址不重复
        if (!sysMenu.getUrl().equals("/")) {
            SysMenu sysMenuOld = SysMenu.dao.findByUrl(sysMenu.getUrl());
            if (sysMenuOld != null) {
                renderFail("菜单地址已存在.");
                return;
            }
        }
        sysMenu.setId(IdUtils.id())
                .setCreater(WebUtils.getSessionUsername(this))
                .setCreateTime(new Date());
        if (sysMenu.save()) {
            renderSuccess(Constant.ADD_SUCCESS);
        } else {
            renderFail(Constant.ADD_FAIL);
        }
    }

    /**
     * 修改 action
     */
    public void updateAction() {
        SysMenu sysMenu = getBean(SysMenu.class, "");

        // 地址不重复
        if (!sysMenu.getUrl().equals("/")) {
            SysMenu sysMenuOld = SysMenu.dao.findByUrl(sysMenu.getUrl());
            if (sysMenuOld != null && !sysMenu.getId().equals(sysMenuOld.getId())) {
                renderFail("菜单地址已存在.");
                return;
            }
        }
        sysMenu.setUpdater(WebUtils.getSessionUsername(this))
                .setUpdateTime(new Date());
        if (sysMenu.update()) {
            renderSuccess(Constant.UPDATE_SUCCESS);
        } else {
            renderFail(Constant.UPDATE_FAIL);
        }
    }


    /**
     * 删除 action
     */
    @Before(IdRequired.class)
    public void deleteAction() {
        String id = getPara("id");
        Db.tx(() -> {
            // 本身 子孙id，
            String sql = "select wm_concat(ID) as IDS from SYS_MENU START WITH ID  = ? CONNECT BY PID = PRIOR ID";
            Record record = Db.findFirst(sql, id);
            if (record.getStr("IDS") == null) {
                return true;
            }

            String ids = record.getStr("IDS").replaceAll(",", "','");
            // 删除机构
            sql = "delete from SYS_MENU where ID in ('" + ids + "')";
            Db.update(sql);

            // 删除 角色 菜单中间表数据
            sql = "delete from SYS_ROLE_MENU where SYS_MENU_ID in ('" + ids + "')";
            Db.update(sql);
            return true;
        });
        renderSuccess(Constant.DELETE_SUCCESS);
    }


    /**
     * org comobTree 数据, 完整的数据
     */
    @Clear(PermissionInterceptor.class)
    public void menuComboTree() {

        List<SysMenu> sysMenus = SysMenu.dao.findAllForTree();
        List<Map<String, Object>> maps = new ArrayList<>();

        Map<String, Object> root = new HashMap<>();
        root.put("id", "0");
        root.put("pid", "-1");
        root.put("text", "根菜单");
        root.put("iconCls", "iconfont icon-root");
        root.put("state", sysMenus.size() > 0 ? "closed" : "open");
        maps.add(root);
        for (SysMenu sysMenu : sysMenus) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", sysMenu.getId());
            map.put("text", sysMenu.getName());
            map.put("pid", sysMenu.getPid());
            map.put("iconCls", sysMenu.getIcon());
            if (sysMenu.getInt("IS_LEAF") == 0) {
                map.put("state", "closed");
            }
            maps.add(map);
        }
        renderJson(maps);
    }


    /**
     * 查看 菜单相关 角色
     */
    public void openMenuRole() {
        setAttr("menuId", getPara("id"));
        render("system/sysMenu_role.ftl");
    }

    /**
     * 菜单相关角色数据
     */
    @Before(SearchSql.class)
    public void queryMenuRole() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);

        Page<SysRoleMenu> sysRoleMenuPage = SysRoleMenu.dao.pageWithRoleInfo(pageNumber, pageSize, where);
        renderDatagrid(sysRoleMenuPage);
    }

    /**
     * 菜单相关角色删除
     */
    public void deleteMenuRole() {
        String menuId = getPara("menuId");
        String roleId = getPara("roleId");
        if (StringUtils.isEmpty(roleId) || StringUtils.isEmpty(menuId)) {
            renderFail("menuId roleId 参数不可为空");
            return;
        }
        SysRoleMenu.dao.deleteById(roleId, menuId);
        renderSuccess("菜单角色删除成功");
    }
}
