package com.mycurdpro.example.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: EX_STAFF_FAMILY  员工家人
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseExStaffFamily<M extends BaseExStaffFamily<M>> extends Model<M> implements IBean {
 



     // 主键id
     public String getId() {
        return getStr("ID");
     }

     public M setId
        (String id) {
        set("ID", id);
        return (M)this;
     }


     // 姓名
     public String getName() {
        return getStr("NAME");
     }

     public M setName
        (String name) {
        set("NAME", name);
        return (M)this;
     }


     // 关系
     public String getRelation() {
        return getStr("RELATION");
     }

     public M setRelation
        (String relation) {
        set("RELATION", relation);
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


     // 工作
     public String getJob() {
        return getStr("JOB");
     }

     public M setJob
        (String job) {
        set("JOB", job);
        return (M)this;
     }


     // 联系方式
     public String getPhone() {
        return getStr("PHONE");
     }

     public M setPhone
        (String phone) {
        set("PHONE", phone);
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
