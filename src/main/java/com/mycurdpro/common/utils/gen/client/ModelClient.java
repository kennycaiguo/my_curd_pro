package com.mycurdpro.common.utils.gen.client;

import com.mycurdpro.common.utils.FileUtils;
import com.mycurdpro.common.utils.freemarker.FreemarkerUtils;
import com.mycurdpro.common.utils.gen.GeneratorConfig;
import com.mycurdpro.common.utils.gen.tools.OracleMetaUtils;
import com.mycurdpro.common.utils.gen.tools.TableMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * model 代码生成器
 *
 * @author zhangchuang
 */
public class ModelClient {
    private final static Logger LOG = LoggerFactory.getLogger(ModelClient.class);

    private final static boolean genModel = true;                                                       // 是否生成Model
    private final static boolean genBaseModel = true;                                                   // 是否生成baseModel
    private final static boolean chainSetter = true;                                                    // 是否生成链式 setter 方法

    private final static String baseModelTplPath = GeneratorConfig.tplBasePath + "baseModel.ftl";     // baseModel 模板文件路径
    private final static String baseModelOutPath = GeneratorConfig.outputBasePath + "model/base/";    // baseModel 文件输出路径
    private final static String modelTplPath = GeneratorConfig.tplBasePath + "model.ftl";             // Model 模板 路径
    private final static String modelOutPath = GeneratorConfig.outputBasePath + "model/";             // Model 文件输出路径

    /**
     * 生成 model
     *
     * @param tableMetas 表元数据集合
     * @throws IOException 文件读写异常
     */
    private static void generate(List<TableMeta> tableMetas) throws IOException {
        LOG.info("(*^▽^*) start generate Model");
        if (!genBaseModel && !genModel) {
            return;
        }
        // 模板文件内容
        String baseModelContent = genBaseModel ? FileUtils.readFile(baseModelTplPath) : null;
        String modelContent = genModel ? FileUtils.readFile(modelTplPath) : null;

        String renderBaseModelContent;  // 渲染后BaseModel文本
        String renderModelContent;      // 渲染后 Model 文本
        String outPath;                 // 文件输出路径

        // 渲染并生成文件
        Map<String, Object> params;
        for (TableMeta tableMeta : tableMetas) {
            params = new HashMap<>();
            params.put("basePackageName", GeneratorConfig.basePackageName);
            params.put("moduleName", GeneratorConfig.moduleName);
            params.put("getterTypeMap", GeneratorConfig.getterTypeMap);
            params.put("chainSetter", chainSetter);
            params.put("tableMeta", tableMeta);
            params.put("author", GeneratorConfig.author);

            // 生成 baseModel
            outPath = baseModelOutPath + "Base" + tableMeta.nameCamelFirstUp + ".java";
            if (genBaseModel) {
                renderBaseModelContent = FreemarkerUtils.renderAsText(baseModelContent, params);
                FileUtils.writeFile(renderBaseModelContent, outPath);
                LOG.info(outPath);
            }

            // 生成Model
            outPath = modelOutPath + tableMeta.nameCamelFirstUp + ".java";
            if (genModel) {
                renderModelContent = FreemarkerUtils.renderAsText(modelContent, params);
                FileUtils.writeFile(renderModelContent, outPath);
                LOG.info(outPath);
            }
        }
        LOG.info("(*^▽^*) generate Model over");
    }

    public static void main(String[] args) throws IOException {
        List<TableMeta> tableMetas = OracleMetaUtils.loadTables(GeneratorConfig.schemaPattern, GeneratorConfig.tableNames, true);
        generate(tableMetas);
    }

}
