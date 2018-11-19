package com.mycurdpro.common.utils.codegenerator;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.activerecord.generator.Generator;

/**
 * jfinal官方 Model 代码生成器
 * maven 项目
 * oracle下存在bug，表字段如设置默认值 生成字典文件时报错 java.sql.SQLException: 流已被关闭
 */
public class ModelGenerator {


    // base model 包名
    private static String baseModelPkg = "com.mycurdpro.testModule.model.base";
    // base 路径
    private static String baseModelDir = PathKit.getWebRootPath() + "/src/main/java/com/mycurdpro/testModule/model/base";
    // model 包名
    private static String modelPkg = "com.mycurdpro.testModule.model";
    // model 路径
    private static String modelDir = baseModelDir + "/..";


    public static void main(String[] args) {
        Generator generator = new Generator(DataSouceUtils.getDataSource(), baseModelPkg, baseModelDir, modelPkg, modelDir);

        // oracle 数据库方言
        generator.setDialect(new OracleDialect());
        // 生成链式调用 代码
        generator.setGenerateChainSetter(true);
        // model 中生成 dao 对象
        generator.setGenerateDaoInModel(true);


        // 生成数据字典
        generator.setGenerateDataDictionary(true);
        generator.setDataDictionaryFileName("dict.txt");
        generator.setDataDictionaryOutputDir(baseModelDir); // base 包下

        // 添加不需要生成的表名
        // generator.addExcludedTable("adv");
        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
        // generator.setRemovedTableNamePrefixes("t_");

        generator.addExcludedTable("SYS_USER_BAK");
        generator.generate();
    }


}
