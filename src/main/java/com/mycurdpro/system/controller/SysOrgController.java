package com.mycurdpro.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.PermissionInterceptor;
import com.mycurdpro.common.utils.Id.IdUtils;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.utils.WebUtils;
import com.mycurdpro.common.validator.IdRequired;
import com.mycurdpro.system.model.SysOrg;

import java.util.*;

/**
 * 组织机构管理
 * @author zhangchuang
 */
public class SysOrgController  extends BaseController {

    public void index(){
        render("system/sysOrg.ftl");
    }

    /**
     * treegrid 查询数据
     */
    public void query(){
        // 初始传递pid 为 0
        String pid = getPara("id", "0");
        String name = getPara("search_LIKE_NAME"); // 根据名曾查询
        List<SysOrg> sysOrgs;
        if(StringUtils.isEmpty(name) || !"0".equals(pid) ){
            // 点开树形菜单
            sysOrgs = SysOrg.dao.findByPid(pid);
        }else{
            // 根据名称搜索
            sysOrgs = SysOrg.dao.findByName(name);
        }
        for(SysOrg sysOrg : sysOrgs){
            if (sysOrg.getInt("IS_LEAF")==0) {
                sysOrg.put("state", "closed");
            }
        }
        renderJson(sysOrgs);
    }

    /**
     * 新增 或 修改 弹窗
     */
    public void newModel(){
        String id = getPara("id");
        if(StringUtils.notEmpty(id)){
            // 编辑
            SysOrg sysOrg = SysOrg.dao.findById(id);
            setAttr("sysOrg",sysOrg);
            if(sysOrg!=null){
                setAttr("pid",sysOrg.getPid());
            }
        }else{
            // 新增
            setAttr("pid",getPara("pid","0"));
        }
        render("system/sysOrg_form.ftl");
    }

    /**
     * 添加 action
     */
    public void addAction(){
        SysOrg sysOrg = getBean(SysOrg.class,"");
        sysOrg.setId(IdUtils.id())
                .setCreater(WebUtils.getSessionUsername(this))
                .setCreateTime(new Date());
        if(sysOrg.save()){
            renderSuccess(Constant.ADD_SUCCESS);
        }else{
            renderFail(Constant.ADD_FAIL);
        }
    }

    /**
     * 修改 action
     */
    public void updateAction(){
        SysOrg sysOrg = getBean(SysOrg.class,"");
        sysOrg.setUpdater(WebUtils.getSessionUsername(this))
                .setUpdateTime(new Date());
        if(sysOrg.update()){
            renderSuccess(Constant.UPDATE_SUCCESS);
        }else{
            renderFail(Constant.UPDATE_FAIL);
        }
    }


    /**
     * 删除 action
     */
    @Before(IdRequired.class)
    public void deleteAction(){
        String id = getPara("id");
        Db.tx(() -> {
            // 本身 子孙id，逗号分隔 TODO
            String sql = "select wm_concat(ID) as IDS from SYS_ORG where PID = ? or ID = ? ";
            Record record = Db.findFirst(sql,id);
            String ids = record.getStr("IDS");

            // 删除机构
            sql = "delete from SYS_ORG where ID in ('"+ids+"')";
            Db.update(sql);

            // 相关 人员 机构字段 置空
            sql = "update SYS_USER set ORG_ID = null where ORG_ID  in ('"+id+"')";
            Db.update(sql);
            return true;
        });
        renderSuccess(Constant.DELETE_SUCCESS);
    }


    /**
     * org comobTree 数据, 完整的数据
     */
    @Clear(PermissionInterceptor.class)
    public void  orgComboTree(){

        List<SysOrg> sysOrgs = SysOrg.dao.findAllForTree();
        List<Map<String, Object>> maps = new ArrayList<>();

        Map<String,Object> root = new HashMap<>();
        root.put("id","0");
        root.put("pid","-1");
        root.put("text","根机构");
        root.put("state","closed");
        maps.add(root);
        for (SysOrg sysOrg : sysOrgs) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", sysOrg.getId());
            map.put("pid", sysOrg.getPid());
            map.put("text", sysOrg.getName());
            System.err.println(sysOrg.getInt("IS_LEAF"));
            if (sysOrg.getInt("IS_LEAF")==0) {
                map.put("state", "closed");
            }
            maps.add(map);
        }
        renderJson(maps);
    }

}
