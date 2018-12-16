package com.mycurdpro.common.interceptor;


import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.Ret;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.utils.Id.IdUtils;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.utils.WebUtils;
import com.mycurdpro.system.model.SysVisitLog;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

/**
 * 后台 访问路径 log 拦截器, 在 模块 route 中配置
 * 全局异常 json
 */
public class ExceptionInterceptor implements Interceptor {
    private final static Logger LOG = LoggerFactory.getLogger(ExceptionInterceptor.class);
    private final static Boolean visitLog = PropKit.use("config.properties").getBoolean("visitLog");

    @Override
    public void intercept(Invocation inv) {
        String errMsg = null;
        try {
            inv.invoke();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            errMsg = ExceptionUtils.getMessage(e);
        }

        if (visitLog) {
            Controller controller = inv.getController();
            SysVisitLog sysVisitLog = new SysVisitLog();
            sysVisitLog.setId(IdUtils.id());
            sysVisitLog.setSysUserIp(WebUtils.getRemoteAddress(controller.getRequest()));
            sysVisitLog.setSysUser(WebUtils.getSessionUsername(controller));
            sysVisitLog.setUrl(inv.getActionKey());
            sysVisitLog.setType(controller.getRequest().getMethod());
            sysVisitLog.setCreateTime(new Date());
            Map<String, String[]> params = controller.getRequest().getParameterMap();
            if (params.keySet().size() > 0) {
                sysVisitLog.setParam(JSON.toJSONString(params));
            }
            sysVisitLog.setError(errMsg);
            sysVisitLog.save();
        }

        // 返回异常信息
        if (StringUtils.notEmpty(errMsg)) {
            String requestType = inv.getController().getRequest().getHeader("X-Requested-With");
            if("XMLHttpRequest".equals(requestType)){
                Ret ret = Ret.create().set("state", "error").set("msg", errMsg);
                inv.getController().renderJson(ret);
            }else{
                inv.getController().setAttr("errorMsg",errMsg);
                inv.getController().render(Constant.VIEW_PATH+"/common/500.ftl");
            }
        }
    }

}