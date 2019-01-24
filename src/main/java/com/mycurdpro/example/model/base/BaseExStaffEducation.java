package com.mycurdpro.example.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: EX_STAFF_EDUCATION  员工教育经历
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseExStaffEducation<M extends BaseExStaffEducation<M>> extends Model<M> implements IBean {
 



     // 主键id
     public String getId() {
        return getStr("ID");
     }

     public M setId
        (String id) {
        set("ID", id);
        return (M)this;
     }


     // 学校名字
     public String getSchoolName() {
        return getStr("SCHOOL_NAME");
     }

     public M setSchoolName
        (String schoolName) {
        set("SCHOOL_NAME", schoolName);
        return (M)this;
     }


     // 等级
     public String getGrade() {
        return getStr("GRADE");
     }

     public M setGrade
        (String grade) {
        set("GRADE", grade);
        return (M)this;
     }


     // 开始时间
     public Date getStartTime() {
        return get("START_TIME");
     }

     public M setStartTime
        (Date startTime) {
        set("START_TIME", startTime);
        return (M)this;
     }


     // 结束时间
     public Date getEndTime() {
        return get("END_TIME");
     }

     public M setEndTime
        (Date endTime) {
        set("END_TIME", endTime);
        return (M)this;
     }


     // 主键id
     public String getExStaffId() {
        return getStr("EX_STAFF_ID");
     }

     public M setExStaffId
        (String exStaffId) {
        set("EX_STAFF_ID", exStaffId);
        return (M)this;
     }


     // 添加人
     public String getCreater() {
        return getStr("CREATER");
     }

     public M setCreater
        (String creater) {
        set("CREATER", creater);
        return (M)this;
     }


     // 添加时间
     public Date getCreateTime() {
        return get("CREATE_TIME");
     }

     public M setCreateTime
        (Date createTime) {
        set("CREATE_TIME", createTime);
        return (M)this;
     }


     // 最后修改人
     public String getUpdater() {
        return getStr("UPDATER");
     }

     public M setUpdater
        (String updater) {
        set("UPDATER", updater);
        return (M)this;
     }


     // 最后修改时间
     public Date getUpdateTime() {
        return get("UPDATE_TIME");
     }

     public M setUpdateTime
        (Date updateTime) {
        set("UPDATE_TIME", updateTime);
        return (M)this;
     }
}
