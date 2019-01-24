package com.mycurdpro.example.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: EX_STAFF  一线员工
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseExStaff<M extends BaseExStaff<M>> extends Model<M> implements IBean {
 



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


     // 性别
     public String getGender() {
        return getStr("GENDER");
     }

     public M setGender
        (String gender) {
        set("GENDER", gender);
        return (M)this;
     }


     // 身份证号
     public String getIdCard() {
        return getStr("ID_CARD");
     }

     public M setIdCard
        (String idCard) {
        set("ID_CARD", idCard);
        return (M)this;
     }


     // 民族
     public String getNation() {
        return getStr("NATION");
     }

     public M setNation
        (String nation) {
        set("NATION", nation);
        return (M)this;
     }


     // 身高CM
     public Integer getHeight() {
        return getInt("HEIGHT");
     }

     public M setHeight
        (Integer height) {
        set("HEIGHT", height);
        return (M)this;
     }


     // 体重KG
     public Integer getWeight() {
        return getInt("WEIGHT");
     }

     public M setWeight
        (Integer weight) {
        set("WEIGHT", weight);
        return (M)this;
     }


     // 职位
     public String getJob() {
        return getStr("JOB");
     }

     public M setJob
        (String job) {
        set("JOB", job);
        return (M)this;
     }


     // 家庭地址
     public String getHomeAddress() {
        return getStr("HOME_ADDRESS");
     }

     public M setHomeAddress
        (String homeAddress) {
        set("HOME_ADDRESS", homeAddress);
        return (M)this;
     }


     // 手机号
     public String getPhoneNumber() {
        return getStr("PHONE_NUMBER");
     }

     public M setPhoneNumber
        (String phoneNumber) {
        set("PHONE_NUMBER", phoneNumber);
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
