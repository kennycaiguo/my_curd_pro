package com.mycurdpro.common.utils.gen.client;


import com.mycurdpro.common.utils.gen.tools.TableMeta;

import java.io.IOException;
import java.util.List;

/**
 * 单表操作代码生成器, Controller  Page
 * @author zhangchuang
 */
public class SingleTableClient {
    // 生成 controlle r
    private final static boolean genController = false;         // 是否生成
    private final static String controllerTplPath = "";         // controller 模板文件路径
    private final static String controllerOutPath = "";         // controller 渲染文件输出路径

    // 生成前台页面
    private final static boolean genPage = false;                           // 是否生成
    private final static String[] pageTplsPath = new String[]{"", "", ""};  // 页面 模板文件路径
    private final static String pageOutDirPath = "";                        // 页面 输出文件输出目录

    public static  void generate(List<TableMeta> tableMetas)throws IOException {

    }
}
