package com.mycurdpro.example;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.mycurdpro.example.model.ExSingleTable;

/**
 * Generated MappingKit
 * example 模块数据表  MappingKit
 *
 * @author zhangchuang
 */
public class ExampleModelMapping {

    public static void mapping(ActiveRecordPlugin arp) {
        // 例子 单表结构
        arp.addMapping("EX_SINGLE_TABLE", "ID", ExSingleTable.class);
    }
}

