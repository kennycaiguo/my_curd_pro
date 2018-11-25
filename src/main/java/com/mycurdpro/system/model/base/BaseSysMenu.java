package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_MENU  系统菜单
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysMenu<M extends BaseSysMenu<M>> extends Model<M> implements IBean {
     // 主键ID
     public String getId() {
        return get("ID");
     }
     public M setId(String id) {
        set("ID", id);
        return (M)this;
     }

     // 菜单名称
     public String getName() {
        return get("NAME");
     }
     public M setName(String name) {
        set("NAME", name);
        return (M)this;
     }

     // 菜单地址
     public String getUrl() {
        return get("URL");
     }
     public M setUrl(String url) {
        set("URL", url);
        return (M)this;
     }

     // 菜单图标
     public String getIcon() {
        return get("ICON");
     }
     public M setIcon(String icon) {
        set("ICON", icon);
        return (M)this;
     }

     // 排序号
     public BigDecimal getSort() {
        return get("SORT");
     }
     public M setSort(BigDecimal sort) {
        set("SORT", sort);
        return (M)this;
     }

     // 父ID
     public String getPid() {
        return get("PID");
     }
     public M setPid(String pid) {
        set("PID", pid);
        return (M)this;
     }

     // 创建人
     public String getCreater() {
        return get("CREATER");
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

     // 最后修改人
     public String getUpdater() {
        return get("UPDATER");
     }
     public M setUpdater(String updater) {
        set("UPDATER", updater);
        return (M)this;
     }

     // 最后修改时间
     public Date getEditTime() {
        return get("EDIT_TIME");
     }
     public M setEditTime(Date editTime) {
        set("EDIT_TIME", editTime);
        return (M)this;
     }

     // 层级
     public BigDecimal getLevel() {
        return get("LEVEL");
     }
     public M setLevel(BigDecimal level) {
        set("LEVEL", level);
        return (M)this;
     }

}
