package com.mycurdpro.example.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Generated baseModel
 * DB table: EX_SINGLE_TABLE  例子 单表结构
 *
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseExSingleTable<M extends BaseExSingleTable<M>> extends Model<M> implements IBean {


    // 主键
    public String getId() {
        return getStr("ID");
    }

    public M setId
            (String id) {
        set("ID", id);
        return (M) this;
    }


    // 姓名
    public String getName() {
        return getStr("NAME");
    }

    public M setName
            (String name) {
        set("NAME", name);
        return (M) this;
    }


    // 年龄
    public BigDecimal getAge() {
        return get("AGE");
    }

    public M setAge
            (BigDecimal age) {
        set("AGE", age);
        return (M) this;
    }


    // 性别
    public String getGender() {
        return getStr("GENDER");
    }

    public M setGender
            (String gender) {
        set("GENDER", gender);
        return (M) this;
    }


    // 添加时间
    public Date getCreateTime() {
        return get("CREATE_TIME");
    }

    public M setCreateTime
            (Date createTime) {
        set("CREATE_TIME", createTime);
        return (M) this;
    }


    // 添加人
    public String getCreater() {
        return getStr("CREATER");
    }

    public M setCreater
            (String creater) {
        set("CREATER", creater);
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


    // 最后修改人
    public String getUpdater() {
        return getStr("UPDATER");
    }

    public M setUpdater
            (String updater) {
        set("UPDATER", updater);
        return (M) this;
    }
}
