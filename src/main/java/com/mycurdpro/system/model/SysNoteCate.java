package com.mycurdpro.system.model;

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

    public List<SysNoteCate> findByUserId(String userId) {
        String sql = "SELECT ID,CATE_TITLE,CATE_REMARK,PID,CREATE_TIME,UPDATE_TIME, connect_by_isleaf AS IS_LEAF FROM SYS_NOTE_CATE where SYS_USER_ID = ?" +
                "  START WITH PID = '0' CONNECT BY PID = PRIOR ID ORDER BY CREATE_TIME" ;
        return find(sql,userId);
    }
}
