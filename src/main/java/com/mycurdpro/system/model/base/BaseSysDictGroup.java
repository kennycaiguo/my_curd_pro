package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_DICT_GROUP  字典编码分组
 *
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysDictGroup<M extends BaseSysDictGroup<M>> extends Model<M> implements IBean {


    // 主键ID
    public String getId() {
        return getStr("ID");
    }

    public M setId
            (String id) {
        set("ID", id);
        return (M) this;
    }


    // 分组名
    public String getGroupName() {
        return getStr("GROUP_NAME");
    }

    public M setGroupName
            (String groupName) {
        set("GROUP_NAME", groupName);
        return (M) this;
    }


    // 分组编码
    public String getGroupCode() {
        return getStr("GROUP_CODE");
    }

    public M setGroupCode
            (String groupCode) {
        set("GROUP_CODE", groupCode);
        return (M) this;
    }


    // 创建人
    public String getCreater() {
        return getStr("CREATER");
    }

    public M setCreater
            (String creater) {
        set("CREATER", creater);
        return (M) this;
    }


    // 创建时间
    public Date getCreateTime() {
        return get("CREATE_TIME");
    }

    public M setCreateTime
            (Date createTime) {
        set("CREATE_TIME", createTime);
        return (M) this;
    }


    // 最后修改人
    public String getUpdater() {
        return getStr("UPDATER");
    }

    public M setUpdater
            (String updater) {
        set("UPDATER", updater);
        return (M) this;
    }


    // 最后修改时间
    public Date getUpdateTime() {
        return get("UPDATE_TIME");
    }

    public M setUpdateTime
            (Date updateTime) {
        set("UPDATE_TIME", updateTime);
        return (M) this;
    }


    // 删除标志，0未删除 1已删除
    public String getDelFlag() {
        return getStr("DEL_FLAG");
    }

    public M setDelFlag
            (String delFlag) {
        set("DEL_FLAG", delFlag);
        return (M) this;
    }
}
