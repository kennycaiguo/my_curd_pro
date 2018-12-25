package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;


/**
 * Generated baseModel
 * DB table: SYS_DATA_REGION  地区数据
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysDataRegion<M extends BaseSysDataRegion<M>> extends Model<M> implements IBean {


     // 
     public String getId() {
        return getStr("ID");
     }

     public M setId
        (String id) {
        set("ID", id);
        return (M)this;
     }


     // 名称
     public String getName() {
        return getStr("NAME");
     }

     public M setName
        (String name) {
        set("NAME", name);
        return (M)this;
     }


     // 父级
     public String getParentId() {
        return getStr("PARENT_ID");
     }

     public M setParentId
        (String parentId) {
        set("PARENT_ID", parentId);
        return (M)this;
     }


     // 简称
     public String getShortName() {
        return getStr("SHORT_NAME");
     }

     public M setShortName
        (String shortName) {
        set("SHORT_NAME", shortName);
        return (M)this;
     }


     // 级别
     public String getLevelType() {
        return getStr("LEVEL_TYPE");
     }

     public M setLevelType
        (String levelType) {
        set("LEVEL_TYPE", levelType);
        return (M)this;
     }


     // 城市代码
     public String getCityCode() {
        return getStr("CITY_CODE");
     }

     public M setCityCode
        (String cityCode) {
        set("CITY_CODE", cityCode);
        return (M)this;
     }


     // 邮政编码
     public String getZipCode() {
        return getStr("ZIP_CODE");
     }

     public M setZipCode
        (String zipCode) {
        set("ZIP_CODE", zipCode);
        return (M)this;
     }


     // 路径名
     public String getMergerName() {
        return getStr("MERGER_NAME");
     }

     public M setMergerName
        (String mergerName) {
        set("MERGER_NAME", mergerName);
        return (M)this;
     }


     // 精度
     public String getLng() {
        return getStr("LNG");
     }

     public M setLng
        (String lng) {
        set("LNG", lng);
        return (M)this;
     }


     // 纬度
     public String getLat() {
        return getStr("LAT");
     }

     public M setLat
        (String lat) {
        set("LAT", lat);
        return (M)this;
     }


     // 拼音
     public String getPinyin() {
        return getStr("PINYIN");
     }

     public M setPinyin
        (String pinyin) {
        set("PINYIN", pinyin);
        return (M)this;
     }


     // 是否禁用 0 未禁 1禁用
     public String getHasDisable() {
        return getStr("HAS_DISABLE");
     }

     public M setHasDisable
        (String hasDisable) {
        set("HAS_DISABLE", hasDisable);
        return (M)this;
     }
}
