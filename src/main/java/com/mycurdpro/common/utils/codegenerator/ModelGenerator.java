package com.mycurdpro.common.utils.codegenerator;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.mycurdpro.common.utils.FileUtils;
import com.mycurdpro.common.utils.freemarker.FreemarkerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model BaseModel MappingKit 数据字典 等代码生成器，相对 Jfinal 官方 代码生成器功能更强
 *
 * @author zhangchuang
 */
public class ModelGenerator {
    private final static Logger LOG = LoggerFactory.getLogger(ModelGenerator.class);

    // 生成文件基础路径 (class 相关目录下)
    private final static String outBasePath = PathKit.getWebRootPath().replaceAll("\\\\", "/")
            + "/src/main/java/" + GeneratorConfig.basePackageName.replaceAll("\\.", "/") + "/" + GeneratorConfig.moduleName + "/";

    //生成 baseModel model
    private final static boolean genModel = true;                                                     // 是否生成Model
    private final static boolean genBaseModel=true;                                                   // 是否生成baseModel
    private final static boolean modelOverwriteIfExist = false;                                        // 是否覆盖Model
    private final static boolean baseModelOverwriteIfExist=true;                                     // 是否覆盖BaseModel
    private final static String baseModelTplPath = GeneratorConfig.tplBasePath + "baseModel.ftl";     // baseModel 模板文件路径
    private final static String baseModelOutPath = outBasePath + "model/base/";                       // baseModel 渲染文件输出路径
    private final static String modelTplPath = GeneratorConfig.tplBasePath + "model.ftl";             // Model 模板 路径
    private final static String modelOutPath = outBasePath + "model/";                                // Model 渲染文件输出路径

    // 生成 MappingKit
    private final static boolean genMappingKit = true;                                                // 是否生成
    private final static boolean mappingKitOverwriteIfExist = true;                                   // 是否覆盖代码
    private final static String mappingKitTplPath = GeneratorConfig.tplBasePath + "ModelMapping.ftl";   // 模板文件路径
    private final static String mappingKitOutPath = outBasePath;                                      // 渲染文件输出路径


    // 数据表字典
    private final static boolean genDict = true;                                                // 是否生成
    private final static boolean dictOverwriteIfExist = true;                                   // 是否覆盖
    private final static boolean genSingleFile = false;                                         // 字典是否单一文件
    private final static String dictTplPath = GeneratorConfig.tplBasePath + "dict.md";          // 字典 模板文件路径
    private final static String dictOutDirPath = outBasePath+"doc/model/";                      // 字典 渲染文件输出目录


    /**
     * 生成字典
     *
     * @param tableMetas 表元数据集合
     * @throws IOException 文件读写异常
     */
    private static void generateDict(List<TableMeta> tableMetas) throws IOException {
        String tplContent = FileUtils.readFile(dictTplPath);  // 模板内容
        String renderContent;        // 渲染后文本
        Map<String, Object> params;  // 模板渲染参数
        String outPath;              // 文件输出路径

        if (genSingleFile) {
            // 生成单个文件
            outPath = dictOutDirPath + "model_dict.md";
            if (dictOverwriteIfExist || !new File(outPath).exists()) {
                params = new HashMap<>();
                params.put("tableMetas", tableMetas);
                params.put("author",GeneratorConfig.author);

                renderContent = FreemarkerUtils.renderAsText(tplContent, params);
                FileUtils.writeFile(renderContent, outPath);
                LOG.info(outPath);
            }
        } else {
            // 生成多个文件
            List<TableMeta> tableMetasTemp;
            for (TableMeta tableMeta : tableMetas) {
                outPath = dictOutDirPath +tableMeta.name + ".md";
                if (!dictOverwriteIfExist && new File(outPath).exists()) {
                    continue;
                }

                tableMetasTemp = new ArrayList<>();
                tableMetasTemp.add(tableMeta);
                params = new HashMap<>();
                params.put("tableMetas", tableMetasTemp);
                params.put("author",GeneratorConfig.author);

                renderContent = FreemarkerUtils.renderAsText(tplContent, params);
                FileUtils.writeFile(renderContent, outPath);
                LOG.info(outPath);
            }
        }
    }


    /**
     * 生成 model
     *
     * @param tableMetas 表元数据集合
     * @throws IOException 文件读写异常
     */
    private static void generateModel(List<TableMeta> tableMetas) throws IOException {
        if(!genBaseModel && genModel){
            return ;
        }
        // 模板文件内容
        String baseModelContent = FileUtils.readFile(baseModelTplPath);
        String modelContent = FileUtils.readFile(modelTplPath);

        String renderBaseModelContent;  // 渲染后BaseModel文本
        String renderModelContent;      // 渲染后 Model 文本
        String outPath;                 // 文件输出路径

        // 渲染并生成文件
        Map<String, Object> params;
        for (TableMeta tableMeta : tableMetas) {
            params = new HashMap<>();
            params.put("basePackageName", GeneratorConfig.basePackageName);
            params.put("moduleName", GeneratorConfig.moduleName);
            params.put("tableMeta", tableMeta);
            params.put("author",GeneratorConfig.author);

            // 生成 baseModel
            outPath = baseModelOutPath + "Base" + tableMeta.nameCamelFirstUp + ".java";
            if (genBaseModel && (baseModelOverwriteIfExist || !new File(outPath).exists())) {
                renderBaseModelContent = FreemarkerUtils.renderAsText(baseModelContent, params);
                FileUtils.writeFile(renderBaseModelContent, outPath);
                LOG.info(outPath);
            }

            // 生成Model
            outPath = modelOutPath + tableMeta.nameCamelFirstUp + ".java";
            if (genModel && (modelOverwriteIfExist || !new File(outPath).exists())) {
                renderModelContent = FreemarkerUtils.renderAsText(modelContent, params);
                FileUtils.writeFile(renderModelContent, outPath);
                LOG.info(outPath);
            }
        }
    }

    /**
     * 生成 ModelMapping
     *
     * @param tableMetas 表元数据集合
     * @throws IOException 文件读写异常
     */
    private static void generateModelMapping(List<TableMeta> tableMetas) throws IOException {
        String outPath = mappingKitOutPath + StrKit.firstCharToUpperCase(GeneratorConfig.moduleName) + "ModelMapping.java";
        if (mappingKitOverwriteIfExist || !new File(outPath).exists()) {
            String mappingKitContent = FileUtils.readFile(mappingKitTplPath);

            Map<String, Object> params = new HashMap<>();
            params.put("basePackageName", GeneratorConfig.basePackageName);
            params.put("moduleName", GeneratorConfig.moduleName);
            params.put("tableMetas", tableMetas);
            params.put("author",GeneratorConfig.author);

            String renderMappingKitContent = FreemarkerUtils.renderAsText(mappingKitContent, params);
            FileUtils.writeFile(renderMappingKitContent, outPath);
            LOG.info(outPath);
        }
    }


    private static void generate() {
        try {
            OracleMetaUtils.dataSource = GeneratorConfig.getDataSource();
            List<TableMeta> tableMetas = OracleMetaUtils.loadTables(GeneratorConfig.schemaPattern, GeneratorConfig.tableNames, true);
            if (genDict) {
                LOG.info("(*^▽^*) start generate dict");
                generateDict(tableMetas);
                LOG.info("(*^▽^*) generate dict over");
            }
            if (genModel) {
                LOG.info("(*^▽^*) start generate Model");
                generateModel(tableMetas);
                LOG.info("(*^▽^*) generate Model over");
            }
            if (genMappingKit) {
                LOG.info("(*^▽^*) start generate MappingKit");
                generateModelMapping(tableMetas);
                LOG.info("(*^▽^*) generate MappingKit over");
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        generate();
    }

}
