package com.mycurdpro.example.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.example.model.base.BaseExStaff;

/**
 * Generated model
 * DB: EX_STAFF  一线员工
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class ExStaff extends BaseExStaff<ExStaff> {
    public static final ExStaff dao = new ExStaff().dao();

    /**
     * 分页查询
     * @param pageNumber 第几页
     * @param pageSize   每页条数
     * @param where      查询条件
     * @return 分页数据
     */
    public Page<ExStaff>  page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from EX_STAFF  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }

}
