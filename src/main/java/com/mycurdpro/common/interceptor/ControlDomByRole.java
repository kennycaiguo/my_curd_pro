package com.mycurdpro.common.interceptor;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.utils.StringUtils;

/**
 * 通过角色编码控制 dom 隐藏显示
 * @author zhangchuang
 */
public class ControlDomByRole implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        inv.getController().setAttr("rolecodes",inv.getController().getSessionAttr(Constant.SYS_USER_ROLE_CODES));
        inv.getController().setAttr("StringUtils",new StringUtils());
        inv.invoke();
    }
}
