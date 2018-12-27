package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.base.BaseSysNote;

import java.util.List;

/**
 * Generated model
 * DB: SYS_NOTE  
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysNote extends BaseSysNote<SysNote> {
    public static final SysNote dao = new SysNote().dao();

    /**
     * 分页查询
     *
     * @param pageNumber 第几页
     * @param pageSize   每页条数
     * @param where      查询条件
     * @return 分页数据
     */
    public Page<SysNote> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select ID,TITLE,TYPE,STATE,CREATE_TIME,UPDATE_TIME ";
        String sqlExceptSelect = " from sys_note  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        sqlExceptSelect += " order by UPDATE_TIME desc,CREATE_TIME asc ";
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }
}
