package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_SERVICE_LOG  业务日志，程序主动记录的重要操作日志
 *
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysServiceLog<M extends BaseSysServiceLog<M>> extends Model<M> implements IBean {


    // 主键ID
    public String getId() {
        return getStr("ID");
    }

    public M setId(String id) {
        set("ID", id);
        return (M) this;
    }


    // 操作人Ip地址
    public String getSysUserIp() {
        return getStr("SYS_USER_IP");
    }

    public M setSysUserIp(String sysUserIp) {
        set("SYS_USER_IP", sysUserIp);
        return (M) this;
    }


    // 访问路径
    public String getUrl() {
        return getStr("URL");
    }

    public M setUrl(String url) {
        set("URL", url);
        return (M) this;
    }


    // 操作内容
    public String getContent() {
        return getStr("CONTENT");
    }

    public M setContent(String content) {
        set("CONTENT", content);
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


    // 操作人
    public String getSysUser() {
        return getStr("SYS_USER");
    }

    public M setSysUser(String sysUser) {
        set("SYS_USER", sysUser);
        return (M) this;
    }
}
