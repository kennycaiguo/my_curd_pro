package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_DICT  码表，枚举值表
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysDict<M extends BaseSysDict<M>> extends Model<M> implements IBean {


     // 主键id
     public String getId() {
        return getStr("ID");
     }

     public M setId(String id) {
        set("ID", id);
        return (M)this;
     }


     // 分类编码
     public String getGroupCode() {
        return getStr("GROUP_CODE");
     }

     public M setGroupCode(String groupCode) {
        set("GROUP_CODE", groupCode);
        return (M)this;
     }


     // 文本
     public String getDictLabel() {
        return getStr("DICT_LABEL");
     }

     public M setDictLabel(String dictLabel) {
        set("DICT_LABEL", dictLabel);
        return (M)this;
     }


     // 文本值
     public String getDictValue() {
        return getStr("DICT_VALUE");
     }

     public M setDictValue(String dictValue) {
        set("DICT_VALUE", dictValue);
        return (M)this;
     }


     // 创建人
     public String getCreater() {
        return getStr("CREATER");
     }

     public M setCreater(String creater) {
        set("CREATER", creater);
        return (M)this;
     }


     // 创建时间
     public Date getCreateTime() {
        return get("CREATE_TIME");
     }

     public M setCreateTime(Date createTime) {
        set("CREATE_TIME", createTime);
        return (M)this;
     }


     // 最后修改人
     public String getUpdater() {
        return getStr("UPDATER");
     }

     public M setUpdater(String updater) {
        set("UPDATER", updater);
        return (M)this;
     }


     // 最后修改时间
     public Date getUpdateTime() {
        return get("UPDATE_TIME");
     }

     public M setUpdateTime(Date updateTime) {
        set("UPDATE_TIME", updateTime);
        return (M)this;
     }


     // 排序号
     public Integer getDictSort() {
        return getInt("DICT_SORT");
     }

     public M setDictSort(Integer dictSort) {
        set("DICT_SORT", dictSort);
        return (M)this;
     }


     // 删除标志，0未删除 1已删除
     public String getDelFlag() {
        return getStr("DEL_FLAG");
     }

     public M setDelFlag(String delFlag) {
        set("DEL_FLAG", delFlag);
        return (M)this;
     }
}
