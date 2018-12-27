package com.mycurdpro.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.SearchSql;
import com.mycurdpro.common.utils.Id.IdUtils;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.utils.WebUtils;
import com.mycurdpro.common.validator.IdRequired;
import com.mycurdpro.common.validator.IdsRequired;
import com.mycurdpro.system.model.SysNote;
import com.mycurdpro.system.model.SysNoteCate;
import com.mycurdpro.system.model.SysUser;

import java.util.*;

/**
 * 个人笔记
 */
public class SysNoteController extends BaseController {
    public void  index(){
        render("system/sysNote.ftl");
    }

    /*----------- 分类管理-------------------*/

    /**
     * 新增弹窗
     */
    public void newCateModel(){
        String id = getPara("id");
        if (StringUtils.notEmpty(id)) {
            SysNoteCate sysNoteCate = SysNoteCate.dao.findById(id);
            setAttr("sysNoteCate", sysNoteCate);
            if (sysNoteCate != null) {
                setAttr("pid", sysNoteCate.getPid());
            }
        } else {
            setAttr("pid", getPara("pid", "0"));
        }
        render("system/sysNoteCate_form.ftl");
    }
    public void queryCate(){
        SysUser sysUser = WebUtils.getSysUser(this);
        List<SysNoteCate> sysNoteCates = SysNoteCate.dao.findByUserId(sysUser.getId());
        List<Map<String,Object>> list = new ArrayList<>();

        for (SysNoteCate sysNoteCate : sysNoteCates) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",sysNoteCate.getId());
            map.put("pid",sysNoteCate.getPid());
            map.put("text",sysNoteCate.getCateTitle());
            if (sysNoteCate.getInt("IS_LEAF") == 0) {
                map.put("state", "closed");
            }
            map.put("iconCls", "iconfont icon-tree-folder");
            map.put("remark",sysNoteCate.getCateRemark());
            map.put("create_time",sysNoteCate.getCreateTime());
            map.put("update_time",sysNoteCate.getUpdateTime());
            list.add(map);
        }

        Map<String,Object> root = new HashMap<>();
        root.put("id", "0");
        root.put("pid", "-1");
        root.put("text", "我的文件夹");
        root.put("state",  "open");
        root.put("iconCls", "iconfont icon-tree-folder");
        list.add(root);

        renderJson(list);
    }

    public void addCateAction(){
        SysNoteCate sysNoteCate = getBean(SysNoteCate.class,"");
        SysUser sysUser = WebUtils.getSysUser(this);
        sysNoteCate.setId(IdUtils.id());
        sysNoteCate.setSysUserId(sysUser.getId());
        sysNoteCate.setCreateTime(new Date());
        if(sysNoteCate.save()){
            renderSuccess(Constant.ADD_SUCCESS);
        }else{
            renderFail(Constant.ADD_FAIL);
        }
    }
    public void updateCateAction(){
        SysUser sysUser = WebUtils.getSysUser(this);
        SysNoteCate sysNoteCate = getBean(SysNoteCate.class,"");
        if(!sysUser.getId().equals(sysNoteCate.getSysUserId())){
            System.err.println("----------------------");
            renderFail(Constant.UPDATE_FAIL);
            return;
        }
        sysNoteCate.setUpdateTime(new Date());
        if(sysNoteCate.update()){
            renderSuccess(Constant.UPDATE_SUCCESS);
        }else{
            renderFail(Constant.UPDATE_FAIL);
        }

    }

    @Before(IdRequired.class)
    public void deleteCateAction(){
        String id = getPara("id");
        SysUser sysUser = WebUtils.getSysUser(this);
        Db.tx(()->{
            String sql = "select wm_concat(ID) as IDS from SYS_NOTE_CATE where SYS_USER_ID = ? START WITH ID  = ? CONNECT BY PID = PRIOR ID";
            Record record = Db.findFirst(sql,sysUser.getId(),id);
            if(record.getStr("IDS")==null){
                return  true;
            }
            String ids = record.getStr("IDS").replaceAll(",", "','");
            sql = " delete from sys_note_cate where id in ('"+ids+"')";
            Db.update(sql);

            sql = "delete from sys_note where cate_id in ('"+ids+"')";
            Db.update(sql);

            return true;
        });
        renderSuccess(Constant.DELETE_SUCCESS);
    }
    /**
     * cate comobTree 数据, 完整的数据
     */
    public void cateComboTree() {
        Boolean withRoot = getParaToBoolean("withRoot", true);
        SysUser sysUser = WebUtils.getSysUser(this);
        List<SysNoteCate> sysNoteCates = SysNoteCate.dao.findByUserId(sysUser.getId());
        List<Map<String, Object>> maps = new ArrayList<>();
        if (withRoot) {
            Map<String, Object> root = new HashMap<>();
            root.put("id", "0");
            root.put("pid", "-1");
            root.put("text", "我的文件夹");
            root.put("state", sysNoteCates.size() > 0 ? "closed" : "open");
            root.put("iconCls", "iconfont icon-tree-folder");
            maps.add(root);
        }
        for (SysNoteCate sysNoteCate : sysNoteCates) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", sysNoteCate.getId());
            map.put("pid", sysNoteCate.getPid());
            map.put("text", sysNoteCate.getCateTitle());
            map.put("iconCls", "iconfont icon-tree-folder");
            if (sysNoteCate.getInt("IS_LEAF") == 0) {
                map.put("state", "closed");
            }
            maps.add(map);
        }
        renderJson(maps);
    }




    /*----------------笔记管理------------------*/
    public void newNoteModel(){
        String id = getPara("id");
        if(StringUtils.notEmpty(id)){
            SysNote sysNote = SysNote.dao.findById(id);
            setAttr("sysNote",sysNote);
        }
        render("system/sysNote_form.ftl");
    }

    @Before(SearchSql.class)
    public void queryNote(){
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysNote> sysNotePage = SysNote.dao.page(pageNumber,pageSize,where);
        renderDatagrid(sysNotePage);
    }

    public void addNoteAction(){
        SysUser sysUser = WebUtils.getSysUser(this);
        SysNote sysNote = getBean(SysNote.class,"");
        sysNote.setId(IdUtils.id())
                .setUserId(sysUser.getId())
                .setCreateTime(new Date())
                .setUpdateTime(new Date());
        if(sysNote.save()){
            renderSuccess(Constant.ADD_SUCCESS);
        }else{
            renderFail(Constant.ADD_FAIL);
        }
    }
    public void updateNoteAction(){
        SysUser sysUser = WebUtils.getSysUser(this);
        SysNote sysNote = getBean(SysNote.class,"");
        if(!sysUser.getId().equals(sysNote.getUserId())){
            renderFail(Constant.UPDATE_FAIL);
            return;
        }

        sysNote.setUpdateTime(new Date());
        if(sysNote.update()){
            renderSuccess(Constant.UPDATE_SUCCESS);
        }else{
            renderFail(Constant.UPDATE_FAIL);
        }
    }

    @Before(IdsRequired.class)
    public void deleteNoteAction(){
        SysUser sysUser = WebUtils.getSysUser(this);
        String ids = getPara("ids").replaceAll(",","','");
        String sql = "delete from sys_note where id in ('"+ids+"') and user_id = ?";
        Db.update(sql,sysUser.getId());
        renderSuccess(Constant.DELETE_SUCCESS);
    }
}
