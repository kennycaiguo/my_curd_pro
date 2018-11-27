package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;


/**
 * Generated baseModel
 * DB table: SYS_NOTICE_TYPE  通知分类
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysNoticeType<M extends BaseSysNoticeType<M>> extends Model<M> implements IBean {
     // 主键id
     public String getId() {
        return get("ID");
     }
     public M setId(String id) {
        set("ID", id);
        return (M)this;
     }

     // 分类编码
     public String getCate() {
        return get("CATE");
     }
     public M setCate(String cate) {
        set("CATE", cate);
        return (M)this;
     }

     // 名称
     public String getName() {
        return get("NAME");
     }
     public M setName(String name) {
        set("NAME", name);
        return (M)this;
     }

     // 消息编码
     public String getCode() {
        return get("CODE");
     }
     public M setCode(String code) {
        set("CODE", code);
        return (M)this;
     }

     // LOGO图标
     public String getLogo() {
        return get("LOGO");
     }
     public M setLogo(String logo) {
        set("LOGO", logo);
        return (M)this;
     }

     // 消息模板
     public String getTemplate() {
        return get("TEMPLATE");
     }
     public M setTemplate(String template) {
        set("TEMPLATE", template);
        return (M)this;
     }

     // 备注
     public String getRemark() {
        return get("REMARK");
     }
     public M setRemark(String remark) {
        set("REMARK", remark);
        return (M)this;
     }

     // 过期天数
     public Integer getUntilExpiryDay() {
        return get("UNTIL_EXPIRY_DAY");
     }
     public M setUntilExpiryDay(Integer untilExpiryDay) {
        set("UNTIL_EXPIRY_DAY", untilExpiryDay);
        return (M)this;
     }

     // 存活天数
     public Integer getUntilDeadDay() {
        return get("UNTIL_DEAD_DAY");
     }
     public M setUntilDeadDay(Integer untilDeadDay) {
        set("UNTIL_DEAD_DAY", untilDeadDay);
        return (M)this;
     }

}
