package ${(basePackageName)!}.${(moduleName)!}.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated baseModel
 * DB: ${(tableMeta.name)!}  ${(tableMeta.remark)!}
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class Base${(tableMeta.nameCamelFirstUp)!}<M extends Base${(tableMeta.nameCamelFirstUp)!}<M>> extends Model<M> implements IBean {

<#if (tableMeta.columnMetas)??>
    <#list tableMeta.columnMetas as column>

     // ${(column.remark)!}
     public M set${(column.nameCamelFirstUp)!}( ${(column.javaType)!} ${(column.nameCamel)!}) {
        set("${(column.name)!}", ${(column.nameCamel)!});
        return (M)this;
     }
     public  ${(column.javaType)!} get${(column.nameCamelFirstUp)!}(${(column.javaType)!} ${(column.nameCamel)!}) {
        return get("${(column.name)!}");
     }

    </#list>
</#if>

}
