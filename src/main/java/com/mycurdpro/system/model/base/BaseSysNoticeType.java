package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_NOTICE_TYPE  通知分类
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysNoticeType<M extends BaseSysNoticeType<M>> extends Model<M> implements IBean {


     // 主键id
     public String getId() {
        return getStr("ID");
     }

     public M setId
        (String id) {
        set("ID", id);
        return (M)this;
     }


     // 分类
     public String getCate() {
        return getStr("CATE");
     }

     public M setCate
        (String cate) {
        set("CATE", cate);
        return (M)this;
     }


     // 名称
     public String getName() {
        return getStr("NAME");
     }

     public M setName
        (String name) {
        set("NAME", name);
        return (M)this;
     }


     // 消息编码
     public String getCode() {
        return getStr("CODE");
     }

     public M setCode
        (String code) {
        set("CODE", code);
        return (M)this;
     }


     // LOGO图标
     public String getLogo() {
        return getStr("LOGO");
     }

     public M setLogo
        (String logo) {
        set("LOGO", logo);
        return (M)this;
     }


     // 消息模板
     public String getTemplate() {
        return getStr("TEMPLATE");
     }

     public M setTemplate
        (String template) {
        set("TEMPLATE", template);
        return (M)this;
     }


     // 备注
     public String getRemark() {
        return getStr("REMARK");
     }

     public M setRemark
        (String remark) {
        set("REMARK", remark);
        return (M)this;
     }


     // 过期天数
     public Integer getUntilExpiryDay() {
        return getInt("UNTIL_EXPIRY_DAY");
     }

     public M setUntilExpiryDay
        (Integer untilExpiryDay) {
        set("UNTIL_EXPIRY_DAY", untilExpiryDay);
        return (M)this;
     }


     // 存活天数
     public Integer getUntilDeadDay() {
        return getInt("UNTIL_DEAD_DAY");
     }

     public M setUntilDeadDay
        (Integer untilDeadDay) {
        set("UNTIL_DEAD_DAY", untilDeadDay);
        return (M)this;
     }


     // 创建人
     public String getCreater() {
        return getStr("CREATER");
     }

     public M setCreater
        (String creater) {
        set("CREATER", creater);
        return (M)this;
     }


     // 创建时间
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
