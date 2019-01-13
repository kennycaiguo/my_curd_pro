package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.base.BaseSysFile;

import java.util.List;

/**
 * Generated model
 * DB: SYS_FILE  用户上传的文件
 *
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysFile extends BaseSysFile<SysFile> {
    public static final SysFile dao = new SysFile().dao();


    /**
     * 根据 多个id 查询列表
     *
     * @param ids
     * @return
     */
    public List<SysFile> findByIds(String ids) {
        String sql = "select * from sys_file where id in (" + ids + ")";
        return find(sql);
    }

    /**
     * 分页查询 可排序
     *
     * @param pageNumber
     * @param pageSize
     * @param sort       排序字段
     * @param order      排序方式
     * @param where
     * @return
     */
    public Page<SysFile> page(int pageNumber, int pageSize, String sort, String order, String where) {
        String sqlSelect = " SELECT sf.*,su.username as username, su.name as name ";
        String sqlExceptSelect = " FROM sys_file sf LEFT JOIN sys_user su ON sf.creater = su.username   ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        if (StringUtils.notEmpty(sort) && StringUtils.notEmpty(order)) {
            sqlExceptSelect += " order by sf." + sort + " " + order;
        } else {
            sqlExceptSelect += "  ORDER BY sf.id ";
        }
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }
}
