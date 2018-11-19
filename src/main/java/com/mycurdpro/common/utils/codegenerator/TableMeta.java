package com.mycurdpro.common.utils.codegenerator;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * TableMeta
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


    @Override
    public String toString() {
        return "TableMeta{" +
                "name='" + name + '\'' +
                ", nameCamel='" + nameCamel + '\'' +
                ", nameCamelFirstUp='" + nameCamelFirstUp + '\'' +
                ", remark='" + remark + '\'' +
                ", primaryKeys=" + primaryKeys +
                ", columnMetas=" + columnMetas +
                '}';
    }
}
