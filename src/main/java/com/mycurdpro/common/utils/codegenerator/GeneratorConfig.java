package com.mycurdpro.common.utils.codegenerator;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * 代码生成器配置
 * 1. tableNames      需要生成代码的表
 * 2. moduleName      生成代码的模块名
 * 3. schemaPattern   表(oracle) 属于的 schema 名
 * 4. basePackageName 基础包名
 * 5. author          代码中 author
 *
 * @author zhangchuang
 */
public class GeneratorConfig {
    // 代码中 @author
    final static String author = "zhangchuang";
    // 基础包名
    final static String basePackageName = "com.mycurdpro";
    // 模式名（oracle)
    final static String schemaPattern = "MYCURD";

    // 模块名
    final static String moduleName = "system";
    // 要生成代码的表名
    final static Set<String> tableNames = new LinkedHashSet<String>() {{
//        add("SYS_USER");
//        add("SYS_USER_ROLE");
//        add("SYS_ROLE");
//        add("SYS_MENU");
//        add("SYS_ROLE_MENU");
//        add("SYS_ROLE_INCODE");
//        add("SYS_USER_ROLEINCODE");
//        add("SYS_ORG");
//        add("SYS_NOTICE_TYPE");
//        add("SYS_NOTICE_TYPE_SYS_ROLE");
//        add("SYS_NOTICE_TYPE_SYS_USER");
//        add("SYS_NOTICE");
//        add("SYS_NOTICE_DETAIL");
//        add("SYS_DICT");
//        add("SYS_DICT_GROUP");
//        add("SYS_FILE");
//        add("SYS_SERVICE_LOG");
//        add("SYS_VISIT_LOG");
//        add("SYS_DATA_REGION");
    }};

    // 模板文件基础路径
    final static String tplBasePath = PathKit.getWebRootPath().replaceAll("\\\\", "/") + "/src/main/java/com/mycurdpro/common/utils/codegenerator/tpl/";

    // baseModel 中生成 getter 方法增强
    final static HashMap<String, String> getterTypeMap = new HashMap<String, String>() {{
        put("String", "getStr");
        put("Integer", "getInt");
        put("Long", "getLong");
        put("Double", "getDouble");
        put("Float", "getFloat");
        put("Short", "getShort");
        put("Byte", "getByte");
    }};

    /**
     * 获得数据库数据源，用于代码生成器
     *
     * @return 数据源
     */
    public static DataSource getDataSource() {
        Prop configProp = PropKit.use("jdbc.properties");
        DruidPlugin dp = new DruidPlugin(configProp.get("jdbc.url"), configProp.get("jdbc.user"),
                configProp.get("jdbc.password"), configProp.get("jdbc.driver"));
        dp.setConnectionProperties("remarksReporting=true");  // oracle 特色， 为了获得列注释
        dp.start();
        return dp.getDataSource();
    }

}
