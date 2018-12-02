package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_USER_ROLEINCODE  用户角色中间表
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysUserRoleincode<M extends BaseSysUserRoleincode<M>> extends Model<M> implements IBean {


     // 用户id
     public String getSysUserId() {
        return getStr("SYS_USER_ID");
     }

     public M setSysUserId(String sysUserId) {
        set("SYS_USER_ID", sysUserId);
        return (M)this;
     }


     // 角色id
     public String getSysRoleincodeId() {
        return getStr("SYS_ROLEINCODE_ID");
     }

     public M setSysRoleincodeId(String sysRoleincodeId) {
        set("SYS_ROLEINCODE_ID", sysRoleincodeId);
        return (M)this;
     }


     // 创建人
     public String getCreater() {
        return getStr("CREATER");
     }

     public M setCreater(String creater) {
        set("CREATER", creater);
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
}
