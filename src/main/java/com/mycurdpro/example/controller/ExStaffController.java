package com.mycurdpro.example.controller;

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
import com.mycurdpro.common.validator.IdRequired;
import com.mycurdpro.common.validator.IdsRequired;
import com.mycurdpro.example.model.ExStaff;
import com.mycurdpro.example.model.ExStaffEducation;
import com.mycurdpro.example.model.ExStaffExperience;
import com.mycurdpro.example.model.ExStaffFamily;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * EX_STAFF 控制器
 * @author zhangchuang
 * @date 2019-01-24 12:58:14
 */
public class ExStaffController extends BaseController{
    private  final  static Logger LOG = LoggerFactory.getLogger(ExStaffController.class);

    /**
     * 列表页
     */
    public void index(){
      render("example/exStaff.ftl");
    }

    /**
     * 列表数据
     */
    @Before(SearchSql.class)
    public void query(){
        int pageNumber=getAttr("pageNumber");
        int pageSize=getAttr("pageSize");
        String where=getAttr(Constant.SEARCH_SQL);
        Page<ExStaff> exStaffPage=ExStaff.dao.page(pageNumber,pageSize,where);
        renderDatagrid(exStaffPage);
    }


    /**
     * 打开新增或者修改弹出框
     */
    public void newModel(){
        String id=getPara("id");
        if(StringUtils.notEmpty(id)){
            ExStaff exStaff=ExStaff.dao.findById(id);
            setAttr("exStaff",exStaff);
            List<ExStaffEducation> exStaffEducations = ExStaffEducation.dao.find("select * from EX_STAFF_EDUCATION where EX_STAFF_ID = ? order by ID ", id);
            setAttr("exStaffEducations", exStaffEducations);
            List<ExStaffExperience> exStaffExperiences = ExStaffExperience.dao.find("select * from EX_STAFF_EXPERIENCE where EX_STAFF_ID = ? order by ID ", id);
            setAttr("exStaffExperiences", exStaffExperiences);
            List<ExStaffFamily> exStaffFamilys = ExStaffFamily.dao.find("select * from EX_STAFF_FAMILY where EX_STAFF_ID = ? order by ID ", id);
            setAttr("exStaffFamilys", exStaffFamilys);
        }
        render("example/exStaff_form.ftl");
    }


    /**
     * 新增 action
     */
    @Before(Tx.class)
    public void addAction(){
        // 新增主表
        ExStaff exStaff=getBean(ExStaff.class,"exStaff");
        exStaff.setId(IdUtils.id())
            .setCreater(WebUtils.getSessionUsername(this))
            .setCreateTime(new Date())
            .save();

        // 新增 子表
        List<ExStaffEducation> exStaffEducations = getBeans(ExStaffEducation.class, "exStaffEducation");
        exStaffEducations.forEach(item ->
                item.setId(IdUtils.id()).setExStaffId(exStaff.getId())
                        .setCreateTime(new Date())
                        .setCreater(WebUtils.getSessionUsername(this)));
        Db.batchSave(exStaffEducations, exStaffEducations.size());

        List<ExStaffExperience> exStaffExperiences = getBeans(ExStaffExperience.class, "exStaffExperience");
        exStaffExperiences.forEach(item ->
                item.setId(IdUtils.id()).setExStaffId(exStaff.getId())
                        .setCreateTime(new Date())
                        .setCreater(WebUtils.getSessionUsername(this)));
        Db.batchSave(exStaffExperiences, exStaffExperiences.size());

        List<ExStaffFamily> exStaffFamilys = getBeans(ExStaffFamily.class, "exStaffFamily");
        exStaffFamilys.forEach(item ->
                item.setId(IdUtils.id()).setExStaffId(exStaff.getId())
                        .setCreateTime(new Date())
                        .setCreater(WebUtils.getSessionUsername(this)));
        Db.batchSave(exStaffFamilys, exStaffFamilys.size());

        renderSuccess(Constant.ADD_SUCCESS);
    }

    /**
     * 修改 action
     */
    @SuppressWarnings("Duplicates")
    @Before(Tx.class)
    public void updateAction(){
        // 修改 主表
        ExStaff exStaff=getBean(ExStaff.class,"exStaff");
        exStaff.setUpdater(WebUtils.getSessionUsername(this))
            .setUpdateTime(new Date())
            .update();

        // 新增或修改 子表
        List<ExStaffEducation> exStaffEducations = getBeans(ExStaffEducation.class, "exStaffEducation");
        exStaffEducations.forEach(item -> {
            item.setExStaffId(exStaff.getId());
            if (StringUtils.isEmpty(item.getId())) {
                item.setId(IdUtils.id()).setCreateTime(new Date())
                        .setCreater(WebUtils.getSessionUsername(this)).save();
            } else {
                item.setUpdateTime(new Date())
                        .setUpdater(WebUtils.getSessionUsername(this)).update();
            }
        });

        List<ExStaffExperience> exStaffExperiences = getBeans(ExStaffExperience.class, "exStaffExperience");
        exStaffExperiences.forEach(item -> {
            item.setExStaffId(exStaff.getId());
            if (StringUtils.isEmpty(item.getId())) {
                item.setId(IdUtils.id()).setCreateTime(new Date())
                        .setCreater(WebUtils.getSessionUsername(this)).save();
            } else {
                item.setUpdateTime(new Date())
                        .setUpdater(WebUtils.getSessionUsername(this)).update();
            }
        });

        List<ExStaffFamily> exStaffFamilys = getBeans(ExStaffFamily.class, "exStaffFamily");
        exStaffFamilys.forEach(item -> {
            item.setExStaffId(exStaff.getId());
            if (StringUtils.isEmpty(item.getId())) {
                item.setId(IdUtils.id()).setCreateTime(new Date())
                        .setCreater(WebUtils.getSessionUsername(this)).save();
            } else {
                item.setUpdateTime(new Date())
                        .setUpdater(WebUtils.getSessionUsername(this)).update();
            }
        });

        renderSuccess(Constant.UPDATE_SUCCESS);
    }

    /**
     * 删除 action
     */
     @Before({IdsRequired.class, Tx.class})
    public void deleteAction(){
        String ids = getPara("ids").replaceAll(",","','");
        String deleteSql;

        // 删从表
        deleteSql = "delete from EX_STAFF_EDUCATION where EX_STAFF_ID in ('" + ids + "')";
        Db.update(deleteSql);
        deleteSql = "delete from EX_STAFF_EXPERIENCE where EX_STAFF_ID in ('" + ids + "')";
        Db.update(deleteSql);
        deleteSql = "delete from EX_STAFF_FAMILY where EX_STAFF_ID in ('" + ids + "')";
        Db.update(deleteSql);
        // 删主表
        deleteSql = "delete from EX_STAFF where id in ( '" + ids + "' ) ";
        Db.update(deleteSql);

        renderSuccess(Constant.DELETE_SUCCESS);
    }


    /**
     * 删子表 EX_STAFF_EDUCATION 员工教育经历  数据
     */
    @Before(IdRequired.class)
    public void deleteExStaffEducationAction() {
        String id = getPara("id");
        ExStaffEducation.dao.deleteById(id);
        renderSuccess(Constant.DELETE_SUCCESS);
    }
    /**
     * 删子表 EX_STAFF_EXPERIENCE 员工工作经历  数据
     */
    @Before(IdRequired.class)
    public void deleteExStaffExperienceAction() {
        String id = getPara("id");
        ExStaffExperience.dao.deleteById(id);
        renderSuccess(Constant.DELETE_SUCCESS);
    }
    /**
     * 删子表 EX_STAFF_FAMILY 员工家人  数据
     */
    @Before(IdRequired.class)
    public void deleteExStaffFamilyAction() {
        String id = getPara("id");
        ExStaffFamily.dao.deleteById(id);
        renderSuccess(Constant.DELETE_SUCCESS);
    }

}
