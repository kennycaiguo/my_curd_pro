package com.mycurdpro.system.model.base;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_VISIT_LOG  系统访问日志
 *
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysVisitLog<M extends BaseSysVisitLog<M>> extends Model<M> implements IBean {


    //----------- 导出 excel 需要， 依赖 get 方法，也可考虑 model 转 bean
    @Excel(name = "访问人", mergeVertical = false, height = 10, width = 30)
    private String sysUser;
    @Excel(name = "访问时间", height = 10, width = 40, databaseFormat = "yyyy-MM-dd HH:mm:ss", format = "yyyy年MM月dd日 HH时mm分ss秒")
    private Date createTime;
    @Excel(name = "ip地址", height = 10, width = 30)
    private String sysUserIp;
    @Excel(name = "请求地址", height = 10, width = 30)
    private String url;
    @Excel(name = "请求类型", height = 10, width = 30)
    private String type;
    // 非本表中字段 get 方法
    //------------ 导出excel 必须


    // 主键id
    public String getId() {
        return getStr("ID");
    }

    public M setId
            (String id) {
        set("ID", id);
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


    // 操作人ip
    public String getSysUserIp() {
        return getStr("SYS_USER_IP");
    }

    public M setSysUserIp
            (String sysUserIp) {
        set("SYS_USER_IP", sysUserIp);
        return (M) this;
    }


    // 访问地址
    public String getUrl() {
        return getStr("URL");
    }

    public M setUrl
            (String url) {
        set("URL", url);
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


    // 访问类型
    public String getType() {
        return getStr("TYPE");
    }

    public M setType
            (String type) {
        set("TYPE", type);
        return (M) this;
    }


    // 参数
    public String getParam() {
        return getStr("PARAM");
    }

    public M setParam
            (String param) {
        set("PARAM", param);
        return (M) this;
    }


    // 异常
    public String getError() {
        return getStr("ERROR");
    }

    public M setError
            (String error) {
        set("ERROR", error);
        return (M) this;
    }
}
