package com.mycurdpro.common.utils.gen.client;

import com.mycurdpro.common.utils.FileUtils;
import com.mycurdpro.common.utils.freemarker.FreemarkerUtils;
import com.mycurdpro.common.utils.gen.GeneratorConfig;
import com.mycurdpro.common.utils.gen.tools.OracleMetaUtils;
import com.mycurdpro.common.utils.gen.tools.TableMeta;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 普通单表操作代码生成器, Controller  Page
 * 如为 树表，自行修改生成后的代码
 *
 * @author zhangchuang
 */
public class SingleTableClient {
    private final static Logger LOG = LoggerFactory.getLogger(SingleTableClient.class);

    private final static String[] excludeFields = new String[]{"CREATE_TIME", "CREATER", "UPDATE_TIME", "UPDATER"};  // 页面中 排除掉的字段

    // 生成 controller
    private final static String controllerTplPath = GeneratorConfig.tplBasePath + "singletable/controller.ftl";// controller 模板文件路径
    private final static String controllerOutPath = GeneratorConfig.outputBasePath + "controller/";             // controller 渲染文件输出路径

    // 生成 页面
    private final static String indexTplPath = GeneratorConfig.tplBasePath + "singletable/index.ftl";                     // 主页面模板路径
    private final static String formTplPath = GeneratorConfig.tplBasePath + "singletable/form.ftl";                      // 表单页模板路径
    private final static String pageOutDirPath = GeneratorConfig.outputBasePath + "views/" + GeneratorConfig.moduleName + "/"; // 页面 输出文件输出目录

    // 生成 导入导出 excel 方法
    private final static boolean hasExcel = false;

    public static void generate(List<TableMeta> tableMetas) throws IOException {
        LOG.info("(*^▽^*) start generate singletable ");
        Map<String, Object> params;
        String controllerTplContent = FileUtils.readFile(controllerTplPath);
        String indexTplContent = FileUtils.readFile(indexTplPath);
        String formTplContent = FileUtils.readFile(formTplPath);

        String renderContent;
        String outPath;
        for (TableMeta tableMeta : tableMetas) {
            params = new HashMap<>();
            params.put("basePackageName", GeneratorConfig.basePackageName);
            params.put("moduleName", GeneratorConfig.moduleName);
            params.put("tableMeta", tableMeta);
            params.put("excludeFields", excludeFields);
            params.put("author", GeneratorConfig.author);
            params.put("generateDate", new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
            params.put("hasExcel", hasExcel);

            // controller
            outPath = controllerOutPath + tableMeta.nameCamelFirstUp + "Controller.java";
            renderContent = FreemarkerUtils.renderAsText(controllerTplContent, params);
            FileUtils.writeFile(renderContent, outPath);
            LOG.info(outPath);

            // index.ftl
            outPath = pageOutDirPath + tableMeta.nameCamel + ".ftl";
            renderContent = FreemarkerUtils.renderAsText(indexTplContent, params);
            FileUtils.writeFile(renderContent, outPath);
            LOG.info(outPath);

            // form.ftl
            outPath = pageOutDirPath + tableMeta.nameCamel + "_form.ftl";
            renderContent = FreemarkerUtils.renderAsText(formTplContent, params);
            FileUtils.writeFile(renderContent, outPath);
            LOG.info(outPath);
        }

        LOG.info("(*^▽^*)  generate singletable over ");
    }

    public static void main(String[] rgs) throws IOException {
        List<TableMeta> tableMetas = OracleMetaUtils.loadTables(GeneratorConfig.schemaPattern, GeneratorConfig.tableNames, true);
        generate(tableMetas);
    }
}
