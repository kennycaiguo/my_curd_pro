package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_NOTE_CATE  
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysNoteCate<M extends BaseSysNoteCate<M>> extends Model<M> implements IBean {


     // 主键
     public String getId() {
        return getStr("ID");
     }

     public M setId
        (String id) {
        set("ID", id);
        return (M)this;
     }


     // 分类名称
     public String getCateTitle() {
        return getStr("CATE_TITLE");
     }

     public M setCateTitle
        (String cateTitle) {
        set("CATE_TITLE", cateTitle);
        return (M)this;
     }


     // 用户id
     public String getSysUserId() {
        return getStr("SYS_USER_ID");
     }

     public M setSysUserId
        (String sysUserId) {
        set("SYS_USER_ID", sysUserId);
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


     // 最后修改时间
     public Date getUpdateTime() {
        return get("UPDATE_TIME");
     }

     public M setUpdateTime
        (Date updateTime) {
        set("UPDATE_TIME", updateTime);
        return (M)this;
     }


     // 备注
     public String getCateRemark() {
        return getStr("CATE_REMARK");
     }

     public M setCateRemark
        (String cateRemark) {
        set("CATE_REMARK", cateRemark);
        return (M)this;
     }


     // 父级id
     public String getPid() {
        return getStr("PID");
     }

     public M setPid
        (String pid) {
        set("PID", pid);
        return (M)this;
     }
}
