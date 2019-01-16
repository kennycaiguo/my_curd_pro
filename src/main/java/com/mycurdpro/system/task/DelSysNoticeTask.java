package com.mycurdpro.system.task;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.mycurdpro.common.utils.Id.IdUtils;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.SysTaskLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 定时任务
 * 删除系统通知
 */
public class DelSysNoticeTask implements Runnable {
    private final static Logger LOG = LoggerFactory.getLogger(DelSysNoticeTask.class);

    @Override
    public void run() {
        // 样板式 代码
        SysTaskLog sysTaskLog = new SysTaskLog();
        sysTaskLog.setId(IdUtils.id());
        sysTaskLog.setClassName(this.getClass().getName());
        sysTaskLog.setStartTime(new Date());
        String errMsg = null;

        Date today = new Date();
        LOG.info(" 定时任务执行。 (删除 “过期未读” 和 “必死” 的 系统通知数据表 数据）");
        try{
            Db.tx(()->{
                // 删除 必死期 的 主从表记录
                String selectSql = "select WM_CONCAT(ID) as IDS from SYS_NOTICE  where DEAD_TIME <= ? ";
                String deleteSql;
                Record record = Db.findFirst(selectSql, today);
                if (StringUtils.notEmpty(record.getStr("IDS"))) {
                    String ids = record.getStr("IDS").replaceAll(",", "','");
                    deleteSql = " delete from  SYS_NOTICE where ID in ('+ids+')";
                    Db.update(deleteSql);
                    deleteSql = " delete from  SYS_NOTICE_DETAIL where SYS_NOTICE_ID in ('+ids+')";
                    Db.update(deleteSql);
                }

                // 删除 过期 未读的从表记录
                selectSql = " select WM_CONCAT(ID) as IDS from SYS_NOTICE where EXPIRY_TIME <= ? ";
                record = Db.findFirst(selectSql, today);
                if (StringUtils.notEmpty(record.getStr("IDS"))) {
                    String ids = record.getStr("IDS").replaceAll(",", "','");
                    deleteSql = " delete from  SYS_NOTICE_DETAIL where SYS_NOTICE_ID in ('+ids+') and HAS_READ='N' ";
                    Db.update(deleteSql);
                }
                return true;
            });
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
            errMsg = e.getMessage();

        }


        // 样板式 代码
        sysTaskLog.setEndTime(new Date());
        if (StringUtils.isEmpty(errMsg)) {
            sysTaskLog.setResult("success");
        } else {
            sysTaskLog.setResult("fail");
            sysTaskLog.setError(errMsg);
        }
        sysTaskLog.save();
    }
}
