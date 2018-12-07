package com.mycurdpro.common.interceptor;


import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.mycurdpro.common.utils.Id.IdUtils;
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
public class VisitLogInterceptor implements Interceptor {
    private final static Logger LOG = LoggerFactory.getLogger(VisitLogInterceptor.class);

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        SysVisitLog sysVisitLog = new SysVisitLog();
        sysVisitLog.setId(IdUtils.id());
        sysVisitLog.setSysUserIp(WebUtils.getRemoteAddress(controller.getRequest()));
        sysVisitLog.setSysUser(WebUtils.getSessionUsername(controller));
        sysVisitLog.setUrl(inv.getActionKey());
        sysVisitLog.setType(controller.getRequest().getMethod());
        sysVisitLog.setCreateTime(new Date());
        Map<String,String[]> params = controller.getRequest().getParameterMap();
        if(params.keySet().size()>0){
            sysVisitLog.setParam(JSON.toJSONString(params));
        }
        try {
            inv.invoke();
            sysVisitLog.save();
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
            sysVisitLog.setError(ExceptionUtils.getMessage(e));
            sysVisitLog.save();
            Ret ret = Ret.create().set("state","error").set("msg", ExceptionUtils.getMessage(e));
            inv.getController().renderJson(ret);
        }

    }

}
