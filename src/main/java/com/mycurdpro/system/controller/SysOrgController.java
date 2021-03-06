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
import com.mycurdpro.system.model.SysOrg;
import com.mycurdpro.system.model.SysUser;

import java.util.*;

/**
 * 组织机构管理
 *
 * @author zhangchuang
 */
public class SysOrgController extends BaseController {

    public void index() {
        render("system/sysOrg.ftl");
    }

    /**
     * treegrid 查询数据
     */
    public void query() {
        // 初始传递pid 为 0
        String name = getPara("search_LIKE_NAME"); // 根据名曾查询
        List<SysOrg> sysOrgs;
        if (StringUtils.isEmpty(name)) {
            // 查所有
            sysOrgs = SysOrg.dao.findAllWithLeafFlag();
        } else {
            // 根据名字查询
            String ids = SysOrg.dao.findIdsByName(name);
            if (StringUtils.notEmpty(ids)) {
                ids = ids.replaceAll(",", "','");
                sysOrgs = SysOrg.dao.findInIds(ids);
            } else {
                sysOrgs = new ArrayList<>();
            }
            for (SysOrg sysOrg : sysOrgs) {
                if (sysOrg.getInt("IS_LEAF") == 0) {
                    sysOrg.put("state", "closed");
                }
            }
        }

        // 图标
        for (SysOrg sysOrg : sysOrgs) {
            sysOrg.put("iconCls", "iconfont icon-orgtree");
        }
        renderJson(sysOrgs);
    }

    /**
     * 新增 或 修改 弹窗
     */
    public void newModel() {
        String id = getPara("id");
        if (StringUtils.notEmpty(id)) {
            // 编辑
            SysOrg sysOrg = SysOrg.dao.findById(id);
            setAttr("sysOrg", sysOrg);
            if (sysOrg != null) {
                setAttr("pid", sysOrg.getPid());
            }
        } else {
            // 新增
            setAttr("pid", getPara("pid", "0"));
        }
        render("system/sysOrg_form.ftl");
    }

    /**
     * 添加 action
     */
    public void addAction() {
        SysOrg sysOrg = getBean(SysOrg.class, "");
        sysOrg.setId(IdUtils.id())
                .setCreater(WebUtils.getSessionUsername(this))
                .setCreateTime(new Date());
        if (sysOrg.save()) {
            renderSuccess(Constant.ADD_SUCCESS);
        } else {
            renderFail(Constant.ADD_FAIL);
        }
    }

    /**
     * 修改 action
     */
    public void updateAction() {
        SysOrg sysOrg = getBean(SysOrg.class, "");
        sysOrg.setUpdater(WebUtils.getSessionUsername(this))
                .setUpdateTime(new Date());
        if (sysOrg.update()) {
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
            String sql = "select wm_concat(ID) as IDS from SYS_ORG START WITH ID  = ? CONNECT BY PID = PRIOR ID";
            Record record = Db.findFirst(sql, id);
            if (record.getStr("IDS") == null) {
                return true;
            }

            String ids = record.getStr("IDS").replaceAll(",", "','");
            // 删除机构
            sql = "delete from SYS_ORG where ID in ('" + ids + "')";
            Db.update(sql);

            // 相关 人员 机构字段 置空
            sql = "update SYS_USER set ORG_ID = null where ORG_ID  in ('" + ids + "')";
            Db.update(sql);
            return true;
        });
        renderSuccess(Constant.DELETE_SUCCESS);
    }


    /**
     * org comobTree 数据, 完整的数据
     */
    @Clear(PermissionInterceptor.class)
    public void orgComboTree() {
        Boolean withRoot = getParaToBoolean("withRoot", true);

        List<SysOrg> sysOrgs = SysOrg.dao.findAllForTree();
        List<Map<String, Object>> maps = new ArrayList<>();

        // 编辑机构时需要
        if (withRoot) {
            Map<String, Object> root = new HashMap<>();
            root.put("id", "0");
            root.put("pid", "-1");
            root.put("text", "根机构");
            root.put("state", sysOrgs.size() > 0 ? "closed" : "open");
            root.put("iconCls", "iconfont icon-orgtree");
            maps.add(root);
        }

        for (SysOrg sysOrg : sysOrgs) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", sysOrg.getId());
            map.put("pid", sysOrg.getPid());
            map.put("text", sysOrg.getName());
            map.put("iconCls", "iconfont icon-orgtree");

            if (sysOrg.getInt("IS_LEAF") == 0) {
                map.put("state", "closed");
            }
            maps.add(map);
        }
        renderJson(maps);
    }


    /**
     * 通过组织机构查询用户 datagrid 数据
     */
    @Before(SearchSql.class)
    public void queryUser() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);

        // 组织机构查询
        String orgId = getPara("extra_orgId");
        if (StringUtils.notEmpty(orgId)) {
            // 机构查询
            Boolean cascadeOrg = getParaToBoolean("extra_cascadeOrg", false);
            String whereSeg;
            if (cascadeOrg) {
                // 级联查询
                String ids = SysOrg.dao.findSonIdsById(orgId);
                if (StringUtils.notEmpty(ids)) {
                    ids = ids.replaceAll(",", "','");
                } else {
                    ids = "unknow";  // 查不到的
                }
                whereSeg = " a.ORG_ID in ('" + ids + "')";
            } else {
                whereSeg = " a.ORG_ID ='" + orgId + "' ";
            }
            if (StringUtils.isEmpty(where)) {
                where += whereSeg;
            } else {
                where += (" and " + whereSeg);
            }
        }

        Page<SysUser> sysUserPage = SysUser.dao.page(pageNumber, pageSize, where);
        renderDatagrid(sysUserPage);
    }

}
