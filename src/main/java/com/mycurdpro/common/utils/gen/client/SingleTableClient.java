package com.mycurdpro.common.utils.gen.client;


import com.mycurdpro.common.utils.gen.tools.TableMeta;

import java.io.IOException;
import java.util.List;

/**
 * 单表操作代码生成器, Controller  Page
 * @author zhangchuang
 */
public class SingleTableClient {

    // 生成 controller  （默认 增删改查)
    private final static String controllerTplPath = "";         // controller 模板文件路径
    private final static String controllerOutPath = "";         // controller 渲染文件输出路径

    // 生成前台页面
    private final static String indexTplPath = "";              // 主页面模板路径
    private final static String formTplPath = "";               // 表单页模板路径
    private final static String viewTplPath = "";               // 查看页模板路径
    private final static String pageOutDirPath = "";            // 页面 输出文件输出目录


    // 额外方法
    private final static boolean genImport = false;   // 通过 excel 导入
    private final static boolean genExport = false;   // excel 导出
    private final static boolean genView = false;     // 生成查看方法

    public static  void generate(List<TableMeta> tableMetas)throws IOException {

    }
}
