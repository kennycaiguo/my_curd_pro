package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_NOTE  
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysNote<M extends BaseSysNote<M>> extends Model<M> implements IBean {


     // 主键
     public String getId() {
        return getStr("ID");
     }

     public M setId
        (String id) {
        set("ID", id);
        return (M)this;
     }


     // 标题
     public String getTitle() {
        return getStr("TITLE");
     }

     public M setTitle
        (String title) {
        set("TITLE", title);
        return (M)this;
     }


     // 类型 html 或 markdown
     public String getType() {
        return getStr("TYPE");
     }

     public M setType
        (String type) {
        set("TYPE", type);
        return (M)this;
     }


     // 内容
     public String getContent() {
        return getStr("CONTENT");
     }

     public M setContent
        (String content) {
        set("CONTENT", content);
        return (M)this;
     }


     // 分类ID
     public String getCateId() {
        return getStr("CATE_ID");
     }

     public M setCateId
        (String cateId) {
        set("CATE_ID", cateId);
        return (M)this;
     }


     // 状态
     public String getState() {
        return getStr("STATE");
     }

     public M setState
        (String state) {
        set("STATE", state);
        return (M)this;
     }


     // 用户id
     public String getUserId() {
        return getStr("USER_ID");
     }

     public M setUserId
        (String userId) {
        set("USER_ID", userId);
        return (M)this;
     }


     // 创建时间(更新时间)
     public Date getCreateTime() {
        return get("CREATE_TIME");
     }

     public M setCreateTime
        (Date createTime) {
        set("CREATE_TIME", createTime);
        return (M)this;
     }

}
