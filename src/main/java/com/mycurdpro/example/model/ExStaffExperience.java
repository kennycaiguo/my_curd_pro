package com.mycurdpro.example.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.example.model.base.BaseExStaffExperience;

/**
 * Generated model
 * DB: EX_STAFF_EXPERIENCE  员工工作经历
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class ExStaffExperience extends BaseExStaffExperience<ExStaffExperience> {
    public static final ExStaffExperience dao = new ExStaffExperience().dao();

    /**
     * 分页查询
     * @param pageNumber 第几页
     * @param pageSize   每页条数
     * @param where      查询条件
     * @return 分页数据
     */
    public Page<ExStaffExperience>  page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from EX_STAFF_EXPERIENCE  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }

}
