package ${(basePackageName)!}.${(moduleName)!}.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.SearchSql;
import com.mycurdpro.common.utils.Id.IdUtils;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.utils.WebUtils;
import com.mycurdpro.common.validator.IdsRequired;
import ${(basePackageName)!}.${(moduleName)!}.model.${(tableMeta.nameCamelFirstUp)!};

import java.util.Date;

/**
 * ${(tableMeta.name)!} 控制器
 * @author ${author!}
 * @date ${generateDate!}
 */
public class ${(tableMeta.nameCamelFirstUp)!}Controller extends BaseController{

        /**
         * 列表页
         */
        public void index(){
          render("${(moduleName)!}/${(tableMeta.nameCamel)!}.ftl");
        }

        /**
         * 列表数据
         */
        @Before(SearchSql.class)
        public void query(){
            int pageNumber=getAttr("pageNumber");
            int pageSize=getAttr("pageSize");
            String where=getAttr(Constant.SEARCH_SQL);
            Page<${(tableMeta.nameCamelFirstUp)!}> ${(tableMeta.nameCamel)!}Page=${(tableMeta.nameCamelFirstUp)!}.dao.page(pageNumber,pageSize,where);
            renderDatagrid(${(tableMeta.nameCamel)!}Page);
        }


        /**
         * 打开新增或者修改弹出框
         */
        public void newModel(){
            String id=getPara("id");
            if(StringUtils.notEmpty(id)){
                ${(tableMeta.nameCamelFirstUp)!} ${(tableMeta.nameCamel)!}=${(tableMeta.nameCamelFirstUp)!}.dao.findById(id);
                setAttr("${(tableMeta.nameCamel)!}",${(tableMeta.nameCamel)!});
            }
            render("${(moduleName)!}/${(tableMeta.nameCamel)!}_form.ftl");
        }


        /**
         * 新增 action
         */
        public void addAction(){
            ${(tableMeta.nameCamelFirstUp)!} ${(tableMeta.nameCamel)!}=getBean(${(tableMeta.nameCamelFirstUp)!}.class,"");
            ${(tableMeta.nameCamel)!}.setId(IdUtils.id())
                .setCreater(WebUtils.getSessionUsername(this))
                .setCreateTime(new Date());
            if(${(tableMeta.nameCamel)!}.save()){
                renderSuccess(Constant.ADD_SUCCESS);
            }else{
                renderFail(Constant.ADD_FAIL);
            }
        }

        /**
         * 修改 action
         */
        public void updateAction(){
            ${(tableMeta.nameCamelFirstUp)!} ${(tableMeta.nameCamel)!}=getBean(${(tableMeta.nameCamelFirstUp)!}.class,"");
            ${(tableMeta.nameCamel)!}.setUpdater(WebUtils.getSessionUsername(this))
                .setUpdateTime(new Date());
            if( ${(tableMeta.nameCamel)!}.update()){
                renderSuccess(Constant.UPDATE_SUCCESS);
            }else{
                renderFail(Constant.UPDATE_FAIL);
            }
        }

        /**
         * 删除 action
         */
        @Before(IdsRequired.class)
        public void deleteAction(){
            String ids = getPara("ids").replaceAll(",","','");
            String deleteSql = "delete from ${(tableMeta.tableName)!} where id in ( '" + ids + "' ) ";
            Db.update(deleteSql);
            renderSuccess(Constant.DELETE_SUCCESS);
        }
}
