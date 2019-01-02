package com.mycurdpro.common.utils.gen.client;

import com.jfinal.kit.StrKit;
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
 * mappingkit 代码生成器
 */
public class ModelMappingClient {
    private final static Logger LOG = LoggerFactory.getLogger(ModelMappingClient.class);

    // 生成 MappingKit
    private final static String mappingKitTplPath = GeneratorConfig.tplBasePath + "ModelMapping.ftl";   // 模板文件路径
    private final static String mappingKitOutPath = GeneratorConfig.outputBasePath;                     // 渲染文件输出路径

    /**
     * 生成 ModelMapping
     *
     * @param tableMetas 表元数据集合
     * @throws IOException 文件读写异常
     */
    private static void generate(List<TableMeta> tableMetas) throws IOException {
        LOG.info("(*^▽^*) start generate MappingKit");

        String mappingKitContent = FileUtils.readFile(mappingKitTplPath);
        Map<String, Object> params = new HashMap<>();
        params.put("basePackageName", GeneratorConfig.basePackageName);
        params.put("moduleName", GeneratorConfig.moduleName);
        params.put("tableMetas", tableMetas);
        params.put("author", GeneratorConfig.author);
        String renderMappingKitContent = FreemarkerUtils.renderAsText(mappingKitContent, params);

        String outPath = mappingKitOutPath + StrKit.firstCharToUpperCase(GeneratorConfig.moduleName) + "ModelMapping.java";
        FileUtils.writeFile(renderMappingKitContent, outPath);

        LOG.info(outPath);
        LOG.info("(*^▽^*) generate MappingKit over");
    }

    public static void main(String[] args) throws IOException {
        List<TableMeta> tableMetas = OracleMetaUtils.loadTables(GeneratorConfig.schemaPattern, GeneratorConfig.tableNames, true);
        generate(tableMetas);
    }
}
