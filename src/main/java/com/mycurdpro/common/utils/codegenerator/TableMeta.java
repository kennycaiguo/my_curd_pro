package com.mycurdpro.common.utils.codegenerator;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TableMeta, 用于代码生成器
 */
public class TableMeta implements Serializable {

    // 表名
    @JSONField(ordinal = 1)
    public String name;

    // 表名 驼峰
    @JSONField(ordinal = 2)
    public String nameCamel;

    // 表名 驼峰 首字大写
    @JSONField(ordinal = 3)
    public String nameCamelFirstUp;

    // 表 备注
    @JSONField(ordinal = 4)
    public String remark;

    // 表 主键
    @JSONField(ordinal = 5)
    public List<String> primaryKeys;

    //表列集合
    @JSONField(ordinal = 6)
    public List<ColumnMeta> columnMetas;


    // base model 中必须 import 的包
    private Set<String> necessaryImport = new HashSet<>();

    public String getName() {
        return name;
    }

    public String getNameCamel() {
        return nameCamel;
    }

    public String getNameCamelFirstUp() {
        return nameCamelFirstUp;
    }

    public String getRemark() {
        return remark;
    }

    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public List<ColumnMeta> getColumnMetas() {
        return columnMetas;
    }

    public void setColumnMetas(List<ColumnMeta> columnMetas) {
        this.columnMetas = columnMetas;
        final HashSet<String> excludeTypes = new HashSet<String>() {{
            add("String");
            add("Double");
            add("Integer");
            add("Float");
            add("Long");
        }};
        for (ColumnMeta columnMeta : columnMetas) {
            if (columnMeta.getJavaTypeShortName() == null || excludeTypes.contains(columnMeta.getJavaTypeShortName())) {
                continue;
            }
            this.necessaryImport.add(columnMeta.javaType);
        }
    }

    public Set<String> getNecessaryImport() {
        return necessaryImport;
    }

    @Override
    public String toString() {
        return "TableMeta{" +
                "name='" + name + '\'' +
                ", nameCamel='" + nameCamel + '\'' +
                ", nameCamelFirstUp='" + nameCamelFirstUp + '\'' +
                ", remark='" + remark + '\'' +
                ", primaryKeys=" + primaryKeys +
                ", columnMetas=" + columnMetas +
                ", necessaryImport=" + necessaryImport +
                '}';
    }
}
