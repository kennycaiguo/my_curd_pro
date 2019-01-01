package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.mycurdpro.system.model.base.BaseSysNoteCate;

import java.util.List;

/**
 * Generated model
 * DB: SYS_NOTE_CATE  
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysNoteCate extends BaseSysNoteCate<SysNoteCate> {
    public static final SysNoteCate dao = new SysNoteCate().dao();

    /**
     * 根据 userid  查询
     * @param userId
     * @return
     */
    public List<SysNoteCate> findByUserId(String userId) {
        String sql = "SELECT ID,CATE_TITLE,CATE_REMARK,PID,CREATE_TIME,UPDATE_TIME, connect_by_isleaf AS IS_LEAF FROM SYS_NOTE_CATE where SYS_USER_ID = ?" +
                "  START WITH PID = '0' CONNECT BY PID = PRIOR ID ORDER BY CREATE_TIME" ;
        return find(sql,userId);
    }


    /**
     * 用户 某个目录的所有 id
     * @param userId
     * @param rootId
     * @return
     */
    public String findIdsByUserAndRoot(String userId,String rootId){
        String sql = "select wm_concat(ID) as IDS from SYS_NOTE_CATE where SYS_USER_ID = ? START WITH ID  = ? CONNECT BY PID = PRIOR ID";
        Record record = Db.findFirst(sql,userId, rootId);
        return  record.getStr("IDS");
    }
}
