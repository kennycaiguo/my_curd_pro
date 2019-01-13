package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_USER  系统用户表
 *
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysUser<M extends BaseSysUser<M>> extends Model<M> implements IBean {


    // 主键id
    public String getId() {
        return getStr("ID");
    }

    public M setId
            (String id) {
        set("ID", id);
        return (M) this;
    }


    // 用户民
    public String getUsername() {
        return getStr("USERNAME");
    }

    public M setUsername
            (String username) {
        set("USERNAME", username);
        return (M) this;
    }


    // 密码
    public String getPassword() {
        return getStr("PASSWORD");
    }

    public M setPassword
            (String password) {
        set("PASSWORD", password);
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


    // 头像
    public String getAvatar() {
        return getStr("AVATAR");
    }

    public M setAvatar
            (String avatar) {
        set("AVATAR", avatar);
        return (M) this;
    }


    // 性别M男F女
    public String getGender() {
        return getStr("GENDER");
    }

    public M setGender
            (String gender) {
        set("GENDER", gender);
        return (M) this;
    }


    // 部门
    public String getOrgId() {
        return getStr("ORG_ID");
    }

    public M setOrgId
            (String orgId) {
        set("ORG_ID", orgId);
        return (M) this;
    }


    // 电子邮箱
    public String getEmail() {
        return getStr("EMAIL");
    }

    public M setEmail
            (String email) {
        set("EMAIL", email);
        return (M) this;
    }


    // 电话
    public String getPhone() {
        return getStr("PHONE");
    }

    public M setPhone
            (String phone) {
        set("PHONE", phone);
        return (M) this;
    }


    // 职位
    public String getJob() {
        return getStr("JOB");
    }

    public M setJob
            (String job) {
        set("JOB", job);
        return (M) this;
    }


    // 职位级别
    public String getJobLevel() {
        return getStr("JOB_LEVEL");
    }

    public M setJobLevel
            (String jobLevel) {
        set("JOB_LEVEL", jobLevel);
        return (M) this;
    }


    // 用户状态 0正常1禁用
    public String getUserState() {
        return getStr("USER_STATE");
    }

    public M setUserState
            (String userState) {
        set("USER_STATE", userState);
        return (M) this;
    }


    // 微信预留
    public String getWx() {
        return getStr("WX");
    }

    public M setWx
            (String wx) {
        set("WX", wx);
        return (M) this;
    }


    // 钉钉预留
    public String getDd() {
        return getStr("DD");
    }

    public M setDd
            (String dd) {
        set("DD", dd);
        return (M) this;
    }


    // 最后登录时间
    public Date getLastLoginTime() {
        return get("LAST_LOGIN_TIME");
    }

    public M setLastLoginTime
            (Date lastLoginTime) {
        set("LAST_LOGIN_TIME", lastLoginTime);
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


    // 创建人
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


    // 删除标志 null 未删除，x 已删除
    public String getDelFlag() {
        return getStr("DEL_FLAG");
    }

    public M setDelFlag
            (String delFlag) {
        set("DEL_FLAG", delFlag);
        return (M) this;
    }
}
