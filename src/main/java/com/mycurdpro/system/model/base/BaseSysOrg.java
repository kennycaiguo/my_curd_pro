package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_ORG  组织机构表
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysOrg<M extends BaseSysOrg<M>> extends Model<M> implements IBean {


     // 主键id
     public String getId() {
        return getStr("ID");
     }

     public M setId(String id) {
        set("ID", id);
        return (M)this;
     }


     // 名称
     public String getName() {
        return getStr("NAME");
     }

     public M setName(String name) {
        set("NAME", name);
        return (M)this;
     }


     // 地址
     public String getAddress() {
        return getStr("ADDRESS");
     }

     public M setAddress(String address) {
        set("ADDRESS", address);
        return (M)this;
     }


     // 备注
     public String getMark() {
        return getStr("MARK");
     }

     public M setMark(String mark) {
        set("MARK", mark);
        return (M)this;
     }


     // 排序号
     public Integer getSort() {
        return getInt("SORT");
     }

     public M setSort(Integer sort) {
        set("SORT", sort);
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


     // 最后修改人
     public String getUpdater() {
        return getStr("UPDATER");
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


     // 父ID
     public String getPid() {
        return getStr("PID");
     }

     public M setPid(String pid) {
        set("PID", pid);
        return (M)this;
     }


     // 主管人
     public String getDirector() {
        return getStr("DIRECTOR");
     }

     public M setDirector(String director) {
        set("DIRECTOR", director);
        return (M)this;
     }


     // 编码
     public String getCode() {
        return getStr("CODE");
     }

     public M setCode(String code) {
        set("CODE", code);
        return (M)this;
     }
}
