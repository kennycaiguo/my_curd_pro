package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

import java.util.Date;

/**
 * Generated baseModel
 * DB table: SYS_TASK_LOG  定时任务日志
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysTaskLog<M extends BaseSysTaskLog<M>> extends Model<M> implements IBean {


     // 主键id
     public String getId() {
        return getStr("ID");
     }

     public M setId
        (String id) {
        set("ID", id);
        return (M)this;
     }


     // 定时任务类名
     public String getClassName() {
        return getStr("CLASS_NAME");
     }

     public M setClassName
        (String className) {
        set("CLASS_NAME", className);
        return (M)this;
     }


     // 开始时间
     public Date getStartTime() {
        return get("START_TIME");
     }

     public M setStartTime
        (Date startTime) {
        set("START_TIME", startTime);
        return (M)this;
     }


     // 结束时间
     public Date getEndTime() {
        return get("END_TIME");
     }

     public M setEndTime
        (Date endTime) {
        set("END_TIME", endTime);
        return (M)this;
     }


     // 运行结果，success 或者 fail
     public String getResult() {
        return getStr("RESULT");
     }

     public M setResult
        (String result) {
        set("RESULT", result);
        return (M)this;
     }


     // 异常信息
     public String getError() {
        return getStr("ERROR");
     }

     public M setError
        (String error) {
        set("ERROR", error);
        return (M)this;
     }
}
