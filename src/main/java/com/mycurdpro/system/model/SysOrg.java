package com.mycurdpro.system.model;

import com.mycurdpro.system.model.base.BaseSysOrg;

import java.util.List;

/**
 * Generated model
 * DB: SYS_ORG  组织机构表
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysOrg extends BaseSysOrg<SysOrg> {
    public static final SysOrg dao = new SysOrg().dao();

    /**
     * 根据 pid 字段查询
     * @param pid
     * @return
     */
    public List<SysOrg> findByPid(String pid){
      String sql = " SELECT ID,PID,NAME,CODE,ADDRESS,MARK,SORT, connect_by_isleaf AS IS_LEAF " +
              " FROM SYS_ORG WHERE PID = ? START WITH PID = ? CONNECT BY PID = PRIOR ID ORDER BY SORT";
      return  find(sql,pid,pid);
    }

    /**
     * 根据name 查询
     * @param name
     * @return
     */
    public List<SysOrg> findByName(String name){
        String sql = " SELECT ID,NAME,CODE,ADDRESS,MARK,SORT, connect_by_isleaf AS IS_LEAF " +
                " FROM SYS_ORG WHERE NAME LIKE '%"+name+"%' START WITH PID = '0' CONNECT BY PID = PRIOR ID ORDER BY SORT";
        return  find(sql);
    }

    /**
     * 查询完整的 org tree
     * @return
     */
    public List<SysOrg> findAllForTree(){
        String sql = " SELECT ID,PID,NAME, connect_by_isleaf AS IS_LEAF  FROM SYS_ORG   START WITH PID = '0' CONNECT BY PID = PRIOR ID ORDER BY IS_LEAF,SORT";
        return find(sql);
    }


}
