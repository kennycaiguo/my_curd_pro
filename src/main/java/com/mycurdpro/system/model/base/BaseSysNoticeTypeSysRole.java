package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_NOTICE_TYPE_SYS_ROLE  系统通知类型角色中间表
 *
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysNoticeTypeSysRole<M extends BaseSysNoticeTypeSysRole<M>> extends Model<M> implements IBean {


    // 通知类型id
    public String getSysNoticeTypeId() {
        return getStr("SYS_NOTICE_TYPE_ID");
    }

    public M setSysNoticeTypeId(String sysNoticeTypeId) {
        set("SYS_NOTICE_TYPE_ID", sysNoticeTypeId);
        return (M) this;
    }


    // 角色id
    public String getSysRoleId() {
        return getStr("SYS_ROLE_ID");
    }

    public M setSysRoleId(String sysRoleId) {
        set("SYS_ROLE_ID", sysRoleId);
        return (M) this;
    }


    // 创建人
    public String getCreater() {
        return getStr("CREATER");
    }

    public M setCreater(String creater) {
        set("CREATER", creater);
        return (M) this;
    }


    // 创建时间
    public Date getCreateTime() {
        return get("CREATE_TIME");
    }

    public M setCreateTime(Date createTime) {
        set("CREATE_TIME", createTime);
        return (M) this;
    }
}
