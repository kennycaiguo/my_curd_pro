package com.mycurdpro.common.utils;

import com.jfinal.core.Controller;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.system.model.SysUser;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {


    /**
     * 获取 http请求  ip地址
     *
     * @param request
     * @return
     */
    public static String getRemoteAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获得session 当前用户 用户名
     *
     * @param controller
     * @return
     */
    public static String getSessionUsername(Controller controller) {
        SysUser sysUser = (SysUser) controller.getSessionAttr(Constant.SYS_USER);
        return sysUser == null ? "debug" : sysUser.getUsername();
    }


    /**
     * 当前登录的系统用户
     *
     * @return
     */
    public static SysUser getSysUser(Controller controller) {
        return controller.getSessionAttr(Constant.SYS_USER);
    }


    /**
     * 获得当前路径
     *
     * @return
     */
    public static String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }


}
