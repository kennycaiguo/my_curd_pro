package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_NOTICE_TYPE_SYS_USER  通知类型系统用户中间表
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysNoticeTypeSysUser<M extends BaseSysNoticeTypeSysUser<M>> extends Model<M> implements IBean {


     // 通知类型id
     public String getSysNoticeTypeId() {
        return getStr("SYS_NOTICE_TYPE_ID");
     }

     public M setSysNoticeTypeId
        (String sysNoticeTypeId) {
        set("SYS_NOTICE_TYPE_ID", sysNoticeTypeId);
        return (M)this;
     }


     // 系统用户id
     public String getSysUserId() {
        return getStr("SYS_USER_ID");
     }

     public M setSysUserId
        (String sysUserId) {
        set("SYS_USER_ID", sysUserId);
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
}
