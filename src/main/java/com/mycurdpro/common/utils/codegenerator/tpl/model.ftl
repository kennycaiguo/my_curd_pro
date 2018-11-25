package ${(basePackageName)!}.${(moduleName)!}.model;

import ${(basePackageName)!}.${(moduleName)!}.model.base.Base${(tableMeta.nameCamelFirstUp)!};

/**
 * Generated model
 * DB: ${(tableMeta.name)!}  ${(tableMeta.remark)!}
 * @author ${(author)!'Generator'}
 */
@SuppressWarnings("serial")
public class ${(tableMeta.nameCamelFirstUp)!} extends Base${(tableMeta.nameCamelFirstUp)!}<${(tableMeta.nameCamelFirstUp)!}> {
    public static final ${(tableMeta.nameCamelFirstUp)!} dao = new ${(tableMeta.nameCamelFirstUp)!}().dao();
}
