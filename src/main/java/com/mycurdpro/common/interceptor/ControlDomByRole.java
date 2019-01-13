package com.mycurdpro.common.interceptor;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.mycurdpro.common.config.Constant;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.Version;

/**
 * 通过角色编码控制 dom 隐藏显示
 *
 * @author zhangchuang
 */
public class ControlDomByRole implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        // 用户角色编码
        inv.getController().setAttr("rolecodes", inv.getController().getSessionAttr(Constant.SYS_USER_ROLE_CODES));

        // 调用静态方法
        BeansWrapper wrapper = new BeansWrapper(new Version(2, 3, 28));
        TemplateModel statics = wrapper.getStaticModels();
        inv.getController().setAttr("statics", statics);

        inv.invoke();
    }
}
