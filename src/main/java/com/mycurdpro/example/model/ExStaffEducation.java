package com.mycurdpro.example.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.example.model.base.BaseExStaffEducation;

/**
 * Generated model
 * DB: EX_STAFF_EDUCATION  员工教育经历
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class ExStaffEducation extends BaseExStaffEducation<ExStaffEducation> {
    public static final ExStaffEducation dao = new ExStaffEducation().dao();

    /**
     * 分页查询
     * @param pageNumber 第几页
     * @param pageSize   每页条数
     * @param where      查询条件
     * @return 分页数据
     */
    public Page<ExStaffEducation>  page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from EX_STAFF_EDUCATION  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }

}
