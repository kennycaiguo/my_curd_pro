package com.mycurdpro.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.HashKit;
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
import com.mycurdpro.system.model.SysUser;

import java.util.Date;

public class SysUserController extends BaseController {

    /**
     * 主页面
     */
    public void index(){
        render("system/sysUser.ftl");
    }


    /**
     * datagrid 数据
     */
    @Before(SearchSql.class)
    public void query(){
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysUser> sysUserPage = SysUser.dao.page(pageNumber,pageSize,where);
        renderDatagrid(sysUserPage);
    }

    /**
     * 新增或者修改弹窗
     */
    public void newModel(){
        String id = getPara("id");
        if(StringUtils.notEmpty(id)){
            SysUser sysUser = SysUser.dao.findById(id);
            setAttr("sysUser",sysUser);
        }
        render("system/sysUser_form.ftl");
    }


    /**
     * add
     */
    public void addAction(){
        SysUser sysUser = getBean(SysUser.class,"");
        sysUser.setId(IdUtils.id()).setCreater(WebUtils.getSessionUsername(this)).setCreateTime(new Date());
        sysUser.setPassword(HashKit.sha1(Constant.USER_DEFAULT_PASSWORD));
        if(sysUser.save()){
            renderSuccess(Constant.ADD_SUCCESS);
        }else{
            renderFail(Constant.ADD_FAIL);
        }

    }

    /**
     * update
     */
    public void updateAction(){
        SysUser sysUser = getBean(SysUser.class,"");
        sysUser.setUpdater(WebUtils.getSessionUsername(this)).setUpdateTime(new Date());
        if(sysUser.update()){
            renderSuccess(Constant.UPDATE_SUCCESS);
        }else{
            renderFail(Constant.UPDATE_FAIL);
        }
    }



    /**
     * delete
     */
    @Before(IdsRequired.class)
    public void deleteAction(){
        String ids = getPara("ids").replaceAll(",","','");
        Db.tx(() -> {
            // 修改删除标志
            String sql = "update sys_user set del_flag = 'x' where id in ('"+ids+"')";
            Db.update(sql);
            return true;
        });
        renderSuccess(Constant.DELETE_SUCCESS);
    }



    /**
     * 重置密码
     */
    @Before(IdsRequired.class)
    public void resetPwd(){
        String ids = getPara("ids").replaceAll(",","','");
        String sha1Pwd = HashKit.sha1(Constant.USER_DEFAULT_PASSWORD);
        String sql = "update sys_user set password = ? where id in ('"+ids+"')";
        Db.update(sql,sha1Pwd);
        renderSuccess("重置密码成功。新密码: "+Constant.USER_DEFAULT_PASSWORD);
    }



}
