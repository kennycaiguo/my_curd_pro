package com.mycurdpro.common.utils.gen;

import com.jfinal.kit.PathKit;

import java.util.*;


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
    // 基础包名
    public final static String basePackageName = "com.mycurdpro";
    // 模块名
    public final static String moduleName = "example";
    // 代码中 @author
    public final static String author = "zhangchuang";
    // 模式名（oracle)
    public final static String schemaPattern = "MYCURD";

    // 要生成代码的表名
    @SuppressWarnings("serial")
    public final static Set<String> tableNames = new LinkedHashSet<String>() {{
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
//        add("SYS_NOTE_CATE");
//        add("SYS_NOTE");
//          add("SYS_TASK_LOG");
        add("EX_SINGLE_TABLE"); // 例子 单表
    }};

    // 生成 代码文件基础路径
    public final static String outputBasePath = "E:/mycurdpro/gencode/" + GeneratorConfig.moduleName + "/";
    /*（生成代码在项目路径下，轻易不要这么干，可能导致代码覆盖)
         public final static String outputBasePath = PathKit.getWebRootPath().replaceAll("\\\\", "/")
        + "/src/main/java/" + GeneratorConfig.basePackageName.replaceAll("\\.", "/") + "/" + GeneratorConfig.moduleName + "/";
    */

    /*------------------------------  以下配置不需要轻易修改，除非明确相关配置的作用-------------------*/


    // 模板文件基础路径
    public final static String tplBasePath = PathKit.getWebRootPath().replaceAll("\\\\", "/") + "/src/main/java/com/mycurdpro/common/utils/gen/tpl/";


    // baseModel 中生成 getter 方法增强
    @SuppressWarnings("serial")
    public final static HashMap<String, String> getterTypeMap = new HashMap<String, String>() {{
        put("String", "getStr");
        put("Integer", "getInt");
        put("Long", "getLong");
        put("Double", "getDouble");
        put("Float", "getFloat");
        put("Short", "getShort");
        put("Byte", "getByte");
    }};

    // 类型长短名映射
    @SuppressWarnings("serial")
    public static final Map<String, String> longShort = new HashMap<String, String>(7) {{
        put("java.util.Date", "Date");
        put("java.lang.String", "String");
        put("java.math.BigDecimal", "BigDecimal");
        put("java.lang.Integer", "Integer");
        put("java.lang.Double", "Double");
        put("java.lang.Float", "Float");
        put("java.lang.Long", "Long");
    }};


    // class 中 不需引入包的数据类型，即基础数据类型
    @SuppressWarnings("serial")
    public static final HashSet<String> excludeImportTypes = new HashSet<String>() {{
        add("String");
        add("Double");
        add("Integer");
        add("Float");
        add("Long");
    }};


}
