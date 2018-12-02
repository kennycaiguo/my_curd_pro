package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_ROLE_MENU  角色菜单中间表
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysRoleMenu<M extends BaseSysRoleMenu<M>> extends Model<M> implements IBean {


     // 角色id
     public String getSysRoleId() {
        return getStr("SYS_ROLE_ID");
     }

     public M setSysRoleId(String sysRoleId) {
        set("SYS_ROLE_ID", sysRoleId);
        return (M)this;
     }


     // 菜单id
     public String getSysMenuId() {
        return getStr("SYS_MENU_ID");
     }

     public M setSysMenuId(String sysMenuId) {
        set("SYS_MENU_ID", sysMenuId);
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
