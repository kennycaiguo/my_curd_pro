package com.mycurdpro.example.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.example.model.base.BaseExSingleTable;

import java.util.List;

/**
 * Generated model
 * DB: EX_SINGLE_TABLE  例子 单表结构
 *
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class ExSingleTable extends BaseExSingleTable<ExSingleTable> {
    public static final ExSingleTable dao = new ExSingleTable().dao();

    /**
     * 分页查询
     *
     * @param pageNumber 第几页
     * @param pageSize   每页条数
     * @param where      查询条件
     * @return 分页数据
     */
    public Page<ExSingleTable> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from EX_SINGLE_TABLE  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }

    /**
     * 根据 where 条件查询
     *
     * @param where
     * @return
     */
    public List<ExSingleTable> findByWhere(String where) {
        String sql = " select * from EX_SINGLE_TABLE ";
        if (StringUtils.notEmpty(where)) {
            sql += " where " + where;
        }
        return find(sql);
    }


    /**
     * 数量查询
     *
     * @param where
     * @return
     */
    public Long findCountByWhere(String where) {
        String sql = " select count(1) as c from EX_SINGLE_TABLE ";
        if (StringUtils.notEmpty(where)) {
            sql += " where " + where;
        }
        return findFirst(sql).getLong("c");
    }
}
