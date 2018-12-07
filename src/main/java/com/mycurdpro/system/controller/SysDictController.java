package com.mycurdpro.system.controller;

import com.google.common.base.Preconditions;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.PermissionInterceptor;
import com.mycurdpro.common.interceptor.SearchSql;
import com.mycurdpro.common.utils.Id.IdUtils;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.utils.WebUtils;
import com.mycurdpro.common.validator.IdsRequired;
import com.mycurdpro.system.model.SysDict;
import com.mycurdpro.system.model.SysDictGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 数据字典
 * @author  zhangchuang
 */
public class SysDictController extends BaseController {

    private final static Logger LOG = LoggerFactory.getLogger(SysDictController.class);

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
        render("system/sysDictGroup_form.ftl");
    }


    /**
     * 增加 sysDictGroup
     */
    public void addGroupAction(){
         SysDictGroup sysDictGroup = getBean(SysDictGroup.class,"");
         sysDictGroup.setId(IdUtils.id());
         sysDictGroup.setCreater(WebUtils.getSessionUsername(this))
                 .setCreateTime(new Date())
                 .setDelFlag("0");
         if(sysDictGroup.save()){
             renderSuccess(Constant.ADD_SUCCESS);
         }else{
             renderFail(Constant.ADD_FAIL);
         }
    }


    /**
     * 修改 sysDictGroup
     */
    public void  updateGroupAction(){
        SysDictGroup sysDictGroup = getBean(SysDictGroup.class,"");
        sysDictGroup.setUpdater(WebUtils.getSessionUsername(this)).setUpdateTime(new Date());
        if(sysDictGroup.update()){
           renderSuccess(Constant.UPDATE_SUCCESS);
        }else{
           renderFail(Constant.UPDATE_FAIL);
        }
    }


    /**
     * 删除 sysDictGroup
     */
    @Before(IdsRequired.class)
    public void  deleteGroupAction(){
        // 更新主从表 (设置删除标志)
        String ids = getPara("ids").replaceAll(",","','");
        Db.tx(() -> {
            String sql = "update sys_dict set del_flag = '1' where group_code in (select group_code from sys_dict_group where id in ('"+ids+"'))";
            Db.update(sql);
            sql = "update sys_dict_group set del_flag = '1' where id in ('"+ids+"')";
            Db.update(sql);
            return true;
        });
        renderSuccess(Constant.DELETE_SUCCESS);
    }


    /**
     * sysDict datagrid
     */
    @Before(SearchSql.class)
    public void queryDict(){
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysDict> sysDictPage = SysDict.dao.page(pageNumber,pageSize,where);
        renderDatagrid(sysDictPage);
    }


    /**
     * 新增或编辑 sysDict 弹窗
     */
    public void newDictModel(){
        String id = getPara("id");
        String groupCode;
        if(StringUtils.notEmpty(id)){
            // 编辑页面
            SysDict sysDict = SysDict.dao.findById(id);
            setAttr("sysDict",sysDict);
            groupCode = sysDict.getGroupCode();
        }else{
            // 新增页面
            groupCode = getPara("groupCode");
        }
        Preconditions.checkNotNull(groupCode,"groupCode 参数不可为空");

        SysDictGroup sysDictGroup = SysDictGroup.dao.findByGroupCode(groupCode);
        if(sysDictGroup!=null){
            setAttr("groupName",sysDictGroup.getGroupName());
            setAttr("groupCode",sysDictGroup.getGroupCode());
        }
        render("system/sysDict_form.ftl");
    }


    /**
     * 增加 sysDict
     */
    public void addDictAction(){
        SysDict sysDict = getBean(SysDict.class,"");
        boolean flag = sysDict.setId(IdUtils.id())
                .setDelFlag("0")
                .setCreater(WebUtils.getSessionUsername(this))
                .setCreateTime(new Date())
                .save();
        if(flag){
            renderSuccess(Constant.ADD_SUCCESS);
        }else{
            renderFail(Constant.ADD_FAIL);
        }
    }


    /**
     * 修改 sysDict
     */
    public void updateDictAction(){
        SysDict sysDict  = getBean(SysDict.class,"");
        boolean flag = sysDict.setUpdater(WebUtils.getSessionUsername(this)).setUpdateTime(new Date()).update();
        if(flag){
            renderSuccess(Constant.UPDATE_SUCCESS);
        }else{
            renderFail(Constant.UPDATE_FAIL);
        }
    }

    /**
     * 删除 sysDict
     */
    @Before(IdsRequired.class)
    public void deleteDictAction(){
        String ids = getPara("ids").replaceAll(",","','");
        String sql = "update sys_dict set del_flag = '1' where id in ('"+ids+"')";
        Db.update(sql);
        renderSuccess(Constant.DELETE_SUCCESS);
    }


    @Clear(PermissionInterceptor.class)
    public void  combobox(){
        String groupCode = getPara("groupCode","");
        renderJson(SysDict.dao.findListByGroupCode(groupCode));
    }
}

