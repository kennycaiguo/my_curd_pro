package com.mycurdpro.example;


import com.jfinal.config.Routes;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.example.controller.ExMainTableController;
import com.mycurdpro.example.controller.ExSingleTableController;
import com.mycurdpro.example.controller.ExStaffController;

/**
 * System 模块路由配置
 *
 * @author zhangchuang
 */
public class ExampleRoute extends Routes {

    @Override
    public void config() {
        // 普通单表
        add("/exSingleTable", ExSingleTableController.class, Constant.VIEW_PATH);
        // 一对多表
        add("/exMainTable", ExMainTableController.class, Constant.VIEW_PATH);

        add("/exStaff", ExStaffController.class, Constant.VIEW_PATH);
    }
}
