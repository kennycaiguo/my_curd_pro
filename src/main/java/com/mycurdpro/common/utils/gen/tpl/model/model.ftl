package ${(basePackageName)!}.${(moduleName)!}.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import ${(basePackageName)!}.${(moduleName)!}.model.base.Base${(tableMeta.nameCamelFirstUp)!};

/**
 * Generated model
 * DB: ${(tableMeta.name)!}  ${(tableMeta.remark)!}
 * @author ${(author)!'Generator'}
 */
@SuppressWarnings("serial")
public class ${(tableMeta.nameCamelFirstUp)!} extends Base${(tableMeta.nameCamelFirstUp)!}<${(tableMeta.nameCamelFirstUp)!}> {
    public static final ${(tableMeta.nameCamelFirstUp)!} dao = new ${(tableMeta.nameCamelFirstUp)!}().dao();

    /**
     * 分页查询
     * @param pageNumber 第几页
     * @param pageSize   每页条数
     * @param where      查询条件
     * @return 分页数据
     */
    public Page<${(tableMeta.nameCamelFirstUp)!}>  page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from ${(tableMeta.name)!}  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }
}
