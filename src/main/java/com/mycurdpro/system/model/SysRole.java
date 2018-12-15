package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.base.BaseSysRole;

import java.util.List;

/**
 * Generated model
 * DB: SYS_ROLE  角色
 *
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysRole extends BaseSysRole<SysRole> {
    public static final SysRole dao = new SysRole().dao();

    /**
     * 角色分页查询，根据排序号正序排序
     *
     * @param pageNumber 第几页
     * @param pageSize   每页条数
     * @param where      查询条件
     * @return 分页数据
     */
    public Page<SysRole> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from sys_role  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        sqlExceptSelect += " order by sort asc ";
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }


    /**
     * 查询所有
     *
     * @return
     */
    public List<SysRole> findAll() {
        String sql = "select * from sys_role ";
        return find(sql);
    }


}
