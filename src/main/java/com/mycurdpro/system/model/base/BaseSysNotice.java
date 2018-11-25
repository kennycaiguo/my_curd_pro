package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_NOTICE  通知消息
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysNotice<M extends BaseSysNotice<M>> extends Model<M> implements IBean {
     // 系统主键
     public String getId() {
        return get("ID");
     }
     public M setId(String id) {
        set("ID", id);
        return (M)this;
     }

     // 通知类型编码
     public String getTypeCode() {
        return get("TYPE_CODE");
     }
     public M setTypeCode(String typeCode) {
        set("TYPE_CODE", typeCode);
        return (M)this;
     }

     // 标题
     public String getTitle() {
        return get("TITLE");
     }
     public M setTitle(String title) {
        set("TITLE", title);
        return (M)this;
     }

     // 内容
     public String getContent() {
        return get("CONTENT");
     }
     public M setContent(String content) {
        set("CONTENT", content);
        return (M)this;
     }

     // 创建时间
     public Date getCreateTime() {
        return get("CREATE_TIME");
     }
     public M setCreateTime(Date createTime) {
        set("CREATE_TIME", createTime);
        return (M)this;
     }

     // 过期时间
     public Date getExpiryTime() {
        return get("EXPIRY_TIME");
     }
     public M setExpiryTime(Date expiryTime) {
        set("EXPIRY_TIME", expiryTime);
        return (M)this;
     }

     // 必死时间
     public Date getDeadTime() {
        return get("DEAD_TIME");
     }
     public M setDeadTime(Date deadTime) {
        set("DEAD_TIME", deadTime);
        return (M)this;
     }

}
