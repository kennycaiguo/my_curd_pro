package com.mycurdpro.common.utils;

import java.math.BigDecimal;
import java.util.Date;

public class DictBean {
    private String id ;
    private String groupCode;
    private String dictLabel;
    private String dictValue;
    private Date createTime;
    private BigDecimal dictSort;
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getDictSort() {
        return dictSort;
    }

    public void setDictSort(BigDecimal dictSort) {
        this.dictSort = dictSort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "DictBean{" +
                "id='" + id + '\'' +
                ", groupCode='" + groupCode + '\'' +
                ", dictLabel='" + dictLabel + '\'' +
                ", dictValue='" + dictValue + '\'' +
                ", createTime=" + createTime +
                ", dictSort=" + dictSort +
                '}';
    }
}
