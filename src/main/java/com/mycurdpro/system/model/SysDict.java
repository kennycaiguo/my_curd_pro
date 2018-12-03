package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.base.BaseSysDict;

import java.util.List;


/**
 * Generated model
 * DB: SYS_DICT  码表，枚举值表
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysDict extends BaseSysDict<SysDict> {

    public static final SysDict dao = new SysDict().dao();

    /**
     * 数据字典分组 SYS_DICT 分页查询，根据排序号 正序排序
     * @param pageNumber 第几页
     * @param pageSize   每页条数
     * @param where      查询条件
     * @return           分页数据
     */
    public Page<SysDict> page(int pageNumber, int pageSize, String where){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from sys_dict  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        sqlExceptSelect += " order by dict_sort asc ";
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }

    /**
     * 根据分组编码查询
     * @param groupCode
     * @return
     */
    public List<SysDict> findListByGroupCode(String groupCode){
        String sql = "select dict_label as label, dict_value as value from sys_dict where group_code = ? order by dict_sort ";
        return find(sql,groupCode);
    }
}
