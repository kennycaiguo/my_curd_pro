package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.mycurdpro.system.model.base.BaseSysMenu;

import java.util.List;

/**
 * Generated model
 * DB: SYS_MENU  系统菜单
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysMenu extends BaseSysMenu<SysMenu> {
    public static final SysMenu dao = new SysMenu().dao();

    /**
     * 查询完整子孙的 Menu
     * @return
     */
    public List<SysMenu> findAll(){
        String sql = "SELECT ID,PID,NAME,URL,ICON,SORT, connect_by_isleaf AS IS_LEAF  FROM SYS_MENU   START WITH PID = '0' CONNECT BY PID = PRIOR ID ORDER BY SORT,IS_LEAF";
        return find(sql);
    }

    /**
     * 查询完整子孙 Menu tree
     * @return
     */
    public List<SysMenu> findAllForTree(){
        String sql = " SELECT ID,PID,NAME,ICON, connect_by_isleaf AS IS_LEAF  FROM SYS_MENU   START WITH PID = '0' CONNECT BY PID = PRIOR ID ORDER BY SORT,IS_LEAF";
        return find(sql);
    }

    /**
     * 通过ids 查询完整子孙 Menu
     * @param ids
     * @return
     */
    public List<SysMenu> findInIds(String ids){
        String sql = "select UNIQUE ID,PID,NAME,URL,ICON,SORT, connect_by_isleaf AS IS_LEAF from SYS_MENU START WITH ID in ('"+ids+"') CONNECT BY PID = PRIOR ID ORDER BY SORT,IS_LEAF";
        return find(sql);
    }

    /**
     * 根据name 查询 到 ids
     * @param name
     * @return
     */
    public String  findIdsByName(String name){
        String sql = "SELECT WM_CONCAT(ID) as IDS FROM SYS_MENU WHERE NAME LIKE '%"+name+"%' ";
        Record record = Db.findFirst(sql);
        return record.getStr("IDS");
    }
}
