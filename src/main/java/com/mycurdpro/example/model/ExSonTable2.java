package com.mycurdpro.example.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.example.model.base.BaseExSonTable2;

/**
 * Generated model
 * DB: EX_SON_TABLE2  测试字表2
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class ExSonTable2 extends BaseExSonTable2<ExSonTable2> {
    public static final ExSonTable2 dao = new ExSonTable2().dao();

    /**
     * 分页查询
     * @param pageNumber 第几页
     * @param pageSize   每页条数
     * @param where      查询条件
     * @return 分页数据
     */
    public Page<ExSonTable2>  page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from EX_SON_TABLE2  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }

}
