package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_VISIT_LOG  系统访问日志
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysVisitLog<M extends BaseSysVisitLog<M>> extends Model<M> implements IBean {
     // 主键id
     public String getId() {
        return get("ID");
     }
     public M setId(String id) {
        set("ID", id);
        return (M)this;
     }

     // 操作人
     public String getSysUser() {
        return get("SYS_USER");
     }
     public M setSysUser(String sysUser) {
        set("SYS_USER", sysUser);
        return (M)this;
     }

     // 操作人ip
     public String getSysUserIp() {
        return get("SYS_USER_IP");
     }
     public M setSysUserIp(String sysUserIp) {
        set("SYS_USER_IP", sysUserIp);
        return (M)this;
     }

     // 访问地址
     public String getUrl() {
        return get("URL");
     }
     public M setUrl(String url) {
        set("URL", url);
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

     // 访问类型
     public String getType() {
        return get("TYPE");
     }
     public M setType(String type) {
        set("TYPE", type);
        return (M)this;
     }

     // 参数
     public String getParam() {
        return get("PARAM");
     }
     public M setParam(String param) {
        set("PARAM", param);
        return (M)this;
     }

}
