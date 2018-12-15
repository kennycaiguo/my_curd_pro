package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.mycurdpro.system.model.base.BaseSysOrg;

import java.util.List;

/**
 * Generated model
 * DB: SYS_ORG  组织机构表
 *
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysOrg extends BaseSysOrg<SysOrg> {
    public static final SysOrg dao = new SysOrg().dao();

    /**
     * 查询完整子孙的 org
     *
     * @return
     */
    public List<SysOrg> findAll() {
        String sql = "SELECT ID,PID,NAME,ADDRESS,CODE,SORT,MARK, connect_by_isleaf AS IS_LEAF  FROM SYS_ORG   START WITH PID = '0' CONNECT BY PID = PRIOR ID ORDER BY SORT,IS_LEAF";
        return find(sql);
    }

    /**
     * 查询完整子孙 org tree
     *
     * @return
     */
    public List<SysOrg> findAllForTree() {
        String sql = " SELECT ID,PID,NAME, connect_by_isleaf AS IS_LEAF  FROM SYS_ORG   START WITH PID = '0' CONNECT BY PID = PRIOR ID ORDER BY SORT,IS_LEAF";
        return find(sql);
    }

    /**
     * 通过ids 查询完整子孙 org
     *
     * @param ids
     * @return
     */
    public List<SysOrg> findInIds(String ids) {
        String sql = "select UNIQUE ID,PID,NAME,ADDRESS,CODE,SORT,MARK, connect_by_isleaf AS IS_LEAF from SYS_ORG START WITH ID in ('" + ids + "') CONNECT BY PID = PRIOR ID ORDER BY SORT,IS_LEAF";
        return find(sql);
    }

    /**
     * 根据name 查询 到 ids
     *
     * @param name
     * @return
     */
    public String findIdsByName(String name) {
        String sql = "SELECT WM_CONCAT(ID) as IDS FROM SYS_ORG WHERE NAME LIKE '%" + name + "%' ";
        Record record = Db.findFirst(sql);
        return record.getStr("IDS");
    }


    /**
     * 通过id 查询 子孙节点 ids, 包括自身
     *
     * @param id
     * @return
     */
    public String findSonIdsById(String id) {
        String sql = "SELECT WM_CONCAT (ID) AS IDS  FROM SYS_ORG START WITH id = ? CONNECT BY PID = PRIOR ID";
        Record record = Db.findFirst(sql, id);
        return record.getStr("IDS");
    }

}
