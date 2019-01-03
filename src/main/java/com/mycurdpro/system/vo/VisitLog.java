package com.mycurdpro.system.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * 测试 model 转 bean
 */
public class VisitLog implements Serializable {
    @Excel(name = "访问人",needMerge = true, height = 20, width = 30)
    private String sysUser;
    @Excel(name = "访问时间", height = 20, width =40, databaseFormat = "yyyy-MM-dd HH:mm:ss",  format = "yyyy年MM月dd日 HH:mm:ss")
    private Date createTime;
    @Excel(name = "ip地址", height = 20, width = 30)
    private  String sysUserIp;
    @Excel(name = "请求地址", height = 20, width = 30)
    private String url;
    @Excel(name = "请求类型", height = 20, width = 30)
    private String type;

    public String getSysUser() {
        return sysUser;
    }

    public void setSysUser(String sysUser) {
        this.sysUser = sysUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSysUserIp() {
        return sysUserIp;
    }

    public void setSysUserIp(String sysUserIp) {
        this.sysUserIp = sysUserIp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
