package com.mycurdpro.common.utils.codegenerator;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * ColumnMeta
 */
public class ColumnMeta implements Serializable {

    // 列名 (默认排序为0)
    @JSONField()
    public String name;

    // 列名 驼峰
    @JSONField(ordinal = 1)
    public String nameCamel;

    // 列名 驼峰 首字大写
    @JSONField(ordinal = 2)
    public String nameCamelFirstUp;

    // 列备注
    @JSONField(ordinal = 3)
    public String remark;

    // 列 数据库类型
    @JSONField(ordinal = 4)
    public String dbType;


    // 长度
    @JSONField(ordinal = 5)
    public Integer size;

    // 小数位数 （如为数值型)
    @JSONField(ordinal = 6)
    public Integer decimalDigits;

    // 列 java类型
    @JSONField(ordinal = 7)
    public String javaType;

    // 是否主键
    @JSONField(ordinal = 8)
    public Boolean isPrimaryKey;

    // 是否允许空值
    @JSONField(ordinal = 9)
    public Boolean isNullable;

    // 默认值
    @JSONField(ordinal = 10)
    public String defaultValue;

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

    public String getDbType() {
        return dbType;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getDecimalDigits() {
        return decimalDigits;
    }

    public String getJavaType() {
        return javaType;
    }

    public Boolean getPrimaryKey() {
        return isPrimaryKey;
    }

    public Boolean getNullable() {
        return isNullable;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        return "ColumnMeta{" +
                "name='" + name + '\'' +
                ", nameCamel='" + nameCamel + '\'' +
                ", nameCamelFirstUp='" + nameCamelFirstUp + '\'' +
                ", remark='" + remark + '\'' +
                ", dbType='" + dbType + '\'' +
                ", size=" + size +
                ", decimalDigits=" + decimalDigits +
                ", javaType='" + javaType + '\'' +
                ", isPrimaryKey=" + isPrimaryKey +
                ", isNullable=" + isNullable +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}
