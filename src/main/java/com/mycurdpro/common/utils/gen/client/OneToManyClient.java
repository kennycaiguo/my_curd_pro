package com.mycurdpro.common.utils.gen.client;

/**
 * 一对多表代码生成器
 *
 * @author zhangchuang
 */
public class OneToManyClient {
    // 主表
    private final static  String MATN_TABLE = "EX_MAIN_TABLE";
    // 字表
    private final static  String[] SON_TABLE = new String[]{
            "EX_SON_TABLE1","EX_SON_TABLE2"
    };

    // 从表中 依赖字段名, 如果 被依赖非 主表ID, 需要自行 修改 controller 代码
    private final static String SON_ID = "MAIN_ID";
}
