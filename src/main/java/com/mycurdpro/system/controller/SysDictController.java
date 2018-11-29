package com.mycurdpro.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.SearchSql;
import com.mycurdpro.common.utils.Id.IdUtils;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.validator.IdRequired;
import com.mycurdpro.system.model.SysDict;
import com.mycurdpro.system.model.SysDictGroup;

import java.util.Date;

/**
 * 数据字典
 * @author  zhangchuang
 */
public class SysDictController extends BaseController {

    /**
     * 页面
     */
    public void index(){
        render("system/sysDict.ftl");
    }


    /**
     * sysDictGroup datagrid
     */
    @Before(SearchSql.class)
    public void  queryGroup(){
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysDictGroup> sysDictGroupPage = SysDictGroup.dao.page(pageNumber,pageSize,where);
        renderDatagrid(sysDictGroupPage);
    }


    /**
     * 新增或编辑sysDictGroup 弹窗
     */
    public  void  newGroupModel(){
        String id = getPara("id");
        if(StringUtils.notEmpty(id)){
            SysDictGroup sysDictGroup = SysDictGroup.dao.findById(id);
            setAttr("sysDictGroup",sysDictGroup);
        }
        render("");
    }


    /**
     * 增加 sysDictGroup
     */
    public void addGroupAction(){
         SysDictGroup sysDictGroup = getBean(SysDictGroup.class,"");
         sysDictGroup.setId(IdUtils.id());
         sysDictGroup.setCreater(getSysUsername())
                 .setCreateTime(new Date())
                 .setDelFlag("0");
         if(sysDictGroup.save()){
             renderSuccess();
         }else{
             renderFail();
         }
    }


    /**
     * 修改 sysDictGroup
     */
    public void  updateGroupAction(){
        SysDictGroup sysDictGroup = getBean(SysDictGroup.class,"");
        sysDictGroup.setUpdater(getSysUsername()).setUpdateTime(new Date());
        if(sysDictGroup.update()){
           renderSuccess();
        }else{
           renderFail();
        }
    }


    /**
     * 删除 sysDictGroup
     */
    @Before(IdRequired.class)
    public void  deleteGroupAction(){
        // 更新主从表 (设置删除标志)
        String ids = getPara("ids").replaceAll(",","','");
        final Boolean[] flag = new Boolean[1];
        Db.tx(() -> {
            int rows1,rows2;
            String sql = "update sys_dict set del_flag = '1' where group_code in (select group_code from sys_dict_group where id in ('"+ids+"'))";
            rows1 = Db.update(sql);
            sql = "update sys_dict_group set del_flag = '1' where id in ('"+ids+"')";
            rows2 = Db.update(sql);
            flag[0] = (rows1 != 0) && (rows2 != 0);
            return flag[0];
        });
        if(flag[0]){
            renderSuccess();
        }else{
            renderFail();
        }
    }


    /**
     * sysDict datagrid
     */
    public void queryDict(){

    }


    /**
     * 新增或编辑 sysDict 弹窗
     */
    public void newDictModel(){
        String id = getPara("id");
        if(StringUtils.notEmpty(id)){
            SysDict sysDict = SysDict.dao.findById(id);
            setAttr("sysDict",sysDict);
        }
        render("");
    }


    /**
     * 增加 sysDict
     */
    public void addDictAction(){
        SysDict sysDict = getBean(SysDict.class,"");
        boolean flag = sysDict.setId(IdUtils.id())
                .setDelFlag("0")
                .setCreater(getSysUsername())
                .setCreateTime(new Date())
                .save();
        if(flag){
            renderSuccess();
        }else{
            renderFail();
        }
    }


    /**
     * 修改 sysDict
     */
    public void updateDictAction(){
        SysDict sysDict  = getBean(SysDict.class,"");
        boolean flag = sysDict.setUpdater(getSysUsername()).setUpdateTime(new Date()).update();
        if(flag){
            renderSuccess();
        }else{
            renderFail();
        }
    }

    /**
     * 删除 sysDict
     */
    @Before(IdRequired.class)
    public void deleteDictAction(){
        // 设置删除标志
        String ids = getPara("ids").replaceAll(",","','");
        String sql = "update sys_dict set del_flag = '1' where id in ('"+ids+"')";
        int row = Db.update(sql);
        if(row==0){
            renderFail();
        }else{
            renderSuccess();
        }
    }
}

