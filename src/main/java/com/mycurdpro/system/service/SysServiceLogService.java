package com.mycurdpro.system.service;


import com.mycurdpro.common.utils.Id.IdUtils;
import com.mycurdpro.system.model.SysServiceLog;

import java.util.Date;

/**
 * 业务日志
 *
 * @author zhangchuang
 */
public class SysServiceLogService {

    /**
     * 添加业务日志
     *
     * @param sysUser   当前登录用户名
     * @param sysUserIp 当前登录用户ip
     * @param url       当前用户访问地址
     * @param content   业务内容
     */
    public static void addServiceLog(String sysUser, String sysUserIp, String url, String content) {
        SysServiceLog serviceLog = new SysServiceLog();
        serviceLog.setId(IdUtils.id())
                .setCreateTime(new Date())
                .setSysUser(sysUser)
                .setSysUserIp(sysUserIp)
                .setUrl(url)
                .setContent(content).save();
    }
}
