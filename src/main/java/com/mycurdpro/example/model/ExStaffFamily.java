package com.mycurdpro.example.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.example.model.base.BaseExStaffFamily;

/**
 * Generated model
 * DB: EX_STAFF_FAMILY  员工家人
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class ExStaffFamily extends BaseExStaffFamily<ExStaffFamily> {
    public static final ExStaffFamily dao = new ExStaffFamily().dao();

    /**
     * 分页查询
     * @param pageNumber 第几页
     * @param pageSize   每页条数
     * @param where      查询条件
     * @return 分页数据
     */
    public Page<ExStaffFamily>  page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from EX_STAFF_FAMILY  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }

}
