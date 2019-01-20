package com.mycurdpro.example.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.example.model.base.BaseExSonTable1;

/**
 * Generated model
 * DB: EX_SON_TABLE1  测试子表1
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class ExSonTable1 extends BaseExSonTable1<ExSonTable1> {
    public static final ExSonTable1 dao = new ExSonTable1().dao();

    /**
     * 分页查询
     * @param pageNumber 第几页
     * @param pageSize   每页条数
     * @param where      查询条件
     * @return 分页数据
     */
    public Page<ExSonTable1>  page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from EX_SON_TABLE1  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }

}
