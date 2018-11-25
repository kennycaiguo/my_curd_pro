package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_ROLE  角色
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysRole<M extends BaseSysRole<M>> extends Model<M> implements IBean {
     // 主键id
     public String getId() {
        return get("ID");
     }
     public M setId(String id) {
        set("ID", id);
        return (M)this;
     }

     // 角色名称
     public String getName() {
        return get("NAME");
     }
     public M setName(String name) {
        set("NAME", name);
        return (M)this;
     }

     // 角色编码
     public String getCode() {
        return get("CODE");
     }
     public M setCode(String code) {
        set("CODE", code);
        return (M)this;
     }

     // 角色描述
     public String getDescription() {
        return get("DESCRIPTION");
     }
     public M setDescription(String description) {
        set("DESCRIPTION", description);
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
     public Date getUpdateTime() {
        return get("UPDATE_TIME");
     }
     public M setUpdateTime(Date updateTime) {
        set("UPDATE_TIME", updateTime);
        return (M)this;
     }

}
