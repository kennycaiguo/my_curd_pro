package com.mycurdpro.system.model;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.system.model.base.BaseSysDictGroup;

/**
 * Generated model
 * DB: SYS_DICT_GROUP  字典编码分组
 *
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysDictGroup extends BaseSysDictGroup<SysDictGroup> {
    public static final SysDictGroup dao = new SysDictGroup().dao();

    /**
     * 数据字典分组 SYS_DICT_GROUP 分页查询，创建时间倒叙排序
     *
     * @param pageNumber 第几页
     * @param pageSize   每页条数
     * @param where      查询条件
     * @return 分页数据
     */
    public Page<SysDictGroup> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from sys_dict_group  ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        sqlExceptSelect += " order by create_time desc ";
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }


    /**
     * 根据 编码查询
     *
     * @param groupCode
     * @return
     */
    public SysDictGroup findByGroupCode(String groupCode) {
        String sql = "select * from sys_dict_group where  group_code = ?";
        return findFirst(sql, groupCode);
    }
}
