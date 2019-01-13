package com.mycurdpro.system.model.base;

import cn.afterturn.easypoi.excel.annotation.Excel;
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

    // ---- excel 导出用
    @Excel(name = "操作人", mergeVertical = false, height = 10, width = 30)
    private String sysUser;
    @Excel(name = "操作人ip", mergeVertical = false, height = 10, width = 30)
    private String sysUserIp;
    @Excel(name = "操作时间", height = 10, width = 40, databaseFormat = "yyyy-MM-dd HH:mm:ss", format = "yyyy年MM月dd日 HH时mm分ss秒")
    private Date createTime;
    @Excel(name = "访问url", mergeVertical = false, height = 10, width = 30)
    private String url;
    @Excel(name = "内容", mergeVertical = false, height = 10, width = 50)
    private String content;
    //----- excel


    // 主键ID，
    public String getId() {
        return getStr("ID");
    }

    public M setId
            (String id) {
        set("ID", id);
        return (M) this;
    }


    // 操作人Ip地址
    public String getSysUserIp() {
        return getStr("SYS_USER_IP");
    }

    public M setSysUserIp
            (String sysUserIp) {
        set("SYS_USER_IP", sysUserIp);
        return (M) this;
    }


    // 访问路径
    public String getUrl() {
        return getStr("URL");
    }

    public M setUrl
            (String url) {
        set("URL", url);
        return (M) this;
    }


    // 操作内容
    public String getContent() {
        return getStr("CONTENT");
    }

    public M setContent
            (String content) {
        set("CONTENT", content);
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


    // 操作人
    public String getSysUser() {
        return getStr("SYS_USER");
    }

    public M setSysUser
            (String sysUser) {
        set("SYS_USER", sysUser);
        return (M) this;
    }
}
