package com.mycurdpro.common.utils;

import com.jfinal.core.Controller;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.system.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WebUtils {

    private final static Logger LOG = LoggerFactory.getLogger(WebUtils.class);

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


    /**
     * 中文文件 下载编码，多浏览器适配
     *
     * @param request
     * @param fileName
     * @return
     */
    public static String buildDownname(HttpServletRequest request, String fileName) {
        String userAgent = request.getHeader("User-Agent");
        try {
            String encodedFileName = URLEncoder.encode(fileName, "UTF8");
            if (userAgent == null) {
                return "filename=\"" + encodedFileName + "\"";
            } else {
                userAgent = userAgent.toLowerCase();
                if (userAgent.indexOf("msie") != -1) {
                    return "filename=\"" + encodedFileName + "\"";
                } else if (userAgent.indexOf("opera") != -1) {
                    return "filename*=UTF-8''" + encodedFileName;
                } else if (userAgent.indexOf("safari") == -1 && userAgent.indexOf("applewebkit") == -1 && userAgent.indexOf("chrome") == -1) {
                    return userAgent.indexOf("mozilla") != -1 ? "filename*=UTF-8''" + encodedFileName : "filename=\"" + encodedFileName + "\"";
                } else {
                    return "filename=\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1") + "\"";
                }
            }
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
            return "";
        }
    }


}
