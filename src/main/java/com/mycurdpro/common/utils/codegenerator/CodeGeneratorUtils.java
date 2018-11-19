package com.mycurdpro.common.utils.codegenerator;


import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.jfinal.kit.PathKit;
import com.mycurdpro.common.utils.FileUtils;
import com.mycurdpro.common.utils.freemarker.FreemarkerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 代码生成器工具
 * 1. 修改项  tableNames    需要生成代码的表
 * 2. 修改项  moduleName    生成代码的模块名
 * 3. 修改项  schemaPattern 表(oracle) 属于的 schema 名
 * 4. 修改项  basePackageName 基础包名
 * <p>
 * 模板文件和代码生成路径根据实际情况而定
 *
 * @author zhangchuang
 */
public class CodeGeneratorUtils {
    private final static Logger LOG = LoggerFactory.getLogger(CodeGeneratorUtils.class);

    // 基础包名
    private final static String basePackageName = "com.mycurdpro";

    // 模块名
    private final static String moduleName = "generate";

    // 模式名
    private final static String schemaPattern = "MYCURD";

    // 要生成代码的表名
    private final static List<String> tableNames = Arrays.asList("SYS_USER_BAK", "SYS_MENU", "SYS_ROLE", "SYS_ORG");

    // 模板文件基础路径
    private final static String tplBasePath = PathKit.getWebRootPath().replaceAll("\\\\", "/") + "/src/main/java/com/mycurdpro/common/utils/codegenerator/tpl/";

    // 生成文件基础路径
    private final static String outBasePath = PathKit.getWebRootPath().replaceAll("\\\\", "/")
            + "/src/main/java/" + basePackageName.replaceAll("\\.", "/") + "/" + moduleName + "/";

    // 是否生成 baseModel model
    private final static boolean genModel = true;
    private final static String baseModelTplPath = tplBasePath + "baseModel.ftl";   // baseModel 模板文件路径
    private final static String baseModelOutPath = outBasePath + "model/base/";     // baseModel 文件生成路径
    private final static String modelTplPath = tplBasePath + "model.ftl";             // Model 模板 路径
    private final static String modelOutPath = outBasePath + "model/";               // Model 生成文件路径

    // 是否生成 controller
    private final static boolean genController = false;
    private final static String controllerTplPath = "";         // controller 模板文件路径
    private final static String controllerOutPath = "";         // controller 文件生成路径

    // 是否生成 service
    private final static boolean genService = false;
    private final static String serviceTplPath = "";          // service 模板文件路径
    private final static String serviceOutPath = "";           // service 文件生成路径

    // 是否生成前台页面
    private final static boolean genPage = false;
    private final static String[] pageTplsPath = new String[]{"", "", ""};  // 页面 模板文件路径
    private final static String pageOutDirPath = "";     // 页面 生成文件目录路径


    // 是否生成数据表字典
    private final static boolean genDict = true;
    private final static boolean genSingleFile = false;                        // 是否单一文件
    private final static String dictTplPath = tplBasePath + "dict.md";          // 字典 模板文件路径
    private final static String dictOutDirPath = outBasePath + "model/base/";   // 字典 生成文件 目录


    /**
     * 获得表元数据
     *
     * @return
     */
    private static List<TableMeta> getTableMetas() {
        OracleMetaUtils.dataSource = DataSouceUtils.getDataSource();
        return OracleMetaUtils.loadTables(schemaPattern, tableNames, true);
    }

    /**
     * 生成字典
     *
     * @param tableMetas
     */
    private static void generateDict(List<TableMeta> tableMetas) {
        LOG.info("开始生成数据字典。");

        try {
            String tplContent = Files.asCharSource(new File(dictTplPath), Charsets.UTF_8).read();
            LOG.debug("templateContent: \n{}", tplContent);
            String renderContent;
            Map<String, Object> params;
            String outPath;

            List<TableMeta> tableMetasParam;
            if (genSingleFile) {
                // 生成多个文件
                params = new HashMap<>();
                params.put("tableMetas", tableMetas);
                renderContent = FreemarkerUtils.renderAsText(tplContent, params);
                outPath = dictOutDirPath + "dict.md";
                FileUtils.writeFile(renderContent, outPath);

                LOG.debug("renderContent:\n{} ", renderContent);
                LOG.info("save as {}", outPath);
            } else {
                // 生成单一文件
                List<TableMeta> tableMetasTemp;
                for (TableMeta tableMeta : tableMetas) {
                    tableMetasTemp = new ArrayList<>();
                    tableMetasTemp.add(tableMeta);
                    params = new HashMap<>();
                    params.put("tableMetas", tableMetasTemp);
                    renderContent = FreemarkerUtils.renderAsText(tplContent, params);
                    outPath = dictOutDirPath + tableMeta.name.toLowerCase() + "_dict.md";
                    FileUtils.writeFile(renderContent, outPath);

                    LOG.debug("renderContent:\n{} ", renderContent);
                    LOG.info("{} save as {}", tableMeta.name, outPath);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        LOG.info("生成数据字典文件完毕 !");
    }


    /**
     * 生成 model
     *
     * @param tableMetas
     */
    private static void generateModel(List<TableMeta> tableMetas) {
        LOG.info("开始生成 Model。");
        try {
            // 加载模板文件内容
            String baseModelContent = Files.asCharSource(new File(baseModelTplPath), Charsets.UTF_8).read();
            LOG.debug("baseModelContent: \n{}", baseModelContent);
            String modelContent = Files.asCharSource(new File(modelTplPath), Charsets.UTF_8).read();
            LOG.debug("modelContent: \n{}", modelContent);

            String renderBaseModelContent;
            String renderModelContent;
            String outPath;

            // 渲染并生成文件
            Map<String, Object> params;
            for (TableMeta tableMeta : tableMetas) {
                LOG.debug("generate {} model", tableMeta.name);

                params = new HashMap<>();
                params.put("basePackageName", basePackageName); // 基础包名
                params.put("moduleName", moduleName);           // 模块名
                params.put("tableMeta", tableMeta);             // 表元数据

                renderBaseModelContent = FreemarkerUtils.renderAsText(baseModelContent, params);
                outPath = baseModelOutPath + "Base" + tableMeta.nameCamelFirstUp + ".java";
                FileUtils.writeFile(renderBaseModelContent, outPath);

                LOG.debug("renderBaseModelContent:\n{} ", renderBaseModelContent);
                LOG.info("{} baseModel save as {}", tableMeta.name, outPath);

                renderModelContent = FreemarkerUtils.renderAsText(modelContent, params);
                outPath = modelOutPath + tableMeta.nameCamelFirstUp + ".java";
                FileUtils.writeFile(renderModelContent, outPath);

                LOG.debug("renderModelContent:\n{} ", renderModelContent);
                LOG.info("{} model save as {}", tableMeta.name, outPath);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        LOG.info("生成Model 完毕 !");
    }


    public static void generate() {
        List<TableMeta> tableMetas = getTableMetas();
        if (genDict) {
            generateDict(tableMetas);
        }
        if (genModel) {
            generateModel(tableMetas);
        }
    }

    public static void main(String[] args) {

        generate();
    }

}
