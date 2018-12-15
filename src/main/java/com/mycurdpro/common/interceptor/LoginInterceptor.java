package com.mycurdpro.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.mycurdpro.common.config.Constant;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 *
 * @author chuang
 */
public class LoginInterceptor implements Interceptor {
    private final static Logger LOG = Logger.getLogger(LoginInterceptor.class);

    @Override
    public void intercept(Invocation invocation) {
        HttpServletRequest request = invocation.getController().getRequest();
        HttpSession session = request.getSession();
        // 未登录 跳转到登录页面
        if (session.getAttribute(Constant.SYS_USER) == null) {
            invocation.getController().redirect("/login");
            return;
        }
        invocation.invoke();
    }
}
