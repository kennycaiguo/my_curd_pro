package com.mycurdpro.system;


import com.jfinal.config.Routes;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.VisitLogInterceptor;
import com.mycurdpro.system.controller.SysDictController;

/**
 * System 模块路由配置
 * @author zhangchuang
 */
public class SystemRoute extends Routes {

    @Override
    public void config() {
        addInterceptor(new VisitLogInterceptor());
        // 数据字典
        add("/sysDict", SysDictController.class, Constant.VIEW_PATH);
    }
}
