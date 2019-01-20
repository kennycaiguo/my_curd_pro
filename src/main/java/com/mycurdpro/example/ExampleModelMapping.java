package com.mycurdpro.example;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.mycurdpro.example.model.ExMainTable;
import com.mycurdpro.example.model.ExSingleTable;
import com.mycurdpro.example.model.ExSonTable1;
import com.mycurdpro.example.model.ExSonTable2;

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
        // 测试主表
        arp.addMapping("EX_MAIN_TABLE", "ID", ExMainTable.class);
        // 测试子表1
        arp.addMapping("EX_SON_TABLE1", "ID", ExSonTable1.class);
        // 测试字表2
        arp.addMapping("EX_SON_TABLE2", "ID", ExSonTable2.class);
    }
}

