package com.mycurdpro.example.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.example.model.base.BaseExMainTable;

/**
 * Generated model
 * DB: EX_MAIN_TABLE  测试主表
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class ExMainTable extends BaseExMainTable<ExMainTable> {
    public static final ExMainTable dao = new ExMainTable().dao();

    /**
     * 分页查询
     * @param pageNumber 第几页
     * @param pageSize   每页条数
     * @param where      查询条件
     * @return 分页数据
     */
    public Page<ExMainTable>  page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from EX_MAIN_TABLE  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        sqlExceptSelect+=" order by CREATE_TIME desc";
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }

}
