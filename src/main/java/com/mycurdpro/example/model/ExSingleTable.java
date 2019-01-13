package com.mycurdpro.example.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.example.model.base.BaseExSingleTable;

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
}
