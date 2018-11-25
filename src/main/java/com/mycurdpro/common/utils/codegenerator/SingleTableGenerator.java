package com.mycurdpro.common.utils.codegenerator;


/**
 * 单表操作代码生成器, Controller Service Page
 *
 * @author zhangchuang
 */
public class SingleTableGenerator {
    // 生成 controller
    private final static boolean genController = false;         // 是否生成
    private final static String controllerTplPath = "";         // controller 模板文件路径
    private final static String controllerOutPath = "";         // controller 渲染文件输出路径

    // 生成 service
    private final static boolean genService = false;           // 是否生成
    private final static String serviceTplPath = "";           // service 模板文件路径
    private final static String serviceOutPath = "";           // service 渲染文件输出路径

    // 生成前台页面
    private final static boolean genPage = false;                           // 是否生成
    private final static String[] pageTplsPath = new String[]{"", "", ""};  // 页面 模板文件路径
    private final static String pageOutDirPath = "";                        // 页面 渲染文件输出目录
}
