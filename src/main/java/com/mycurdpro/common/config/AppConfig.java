package com.mycurdpro.common.config;


import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.json.MixedJsonFactory;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.*;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.druid.IDruidStatViewAuth;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.mycurdpro.LoginController;
import com.mycurdpro.MainController;
import com.mycurdpro.common.interceptor.ExceptionInterceptor;
import com.mycurdpro.common.interceptor.LoginInterceptor;
import com.mycurdpro.common.interceptor.PermissionInterceptor;
import com.mycurdpro.common.utils.UtilsController;
import com.mycurdpro.system.SystemModelMapping;
import com.mycurdpro.system.SystemRoute;
import com.mycurdpro.system.model.SysUser;
import it.sauronsoftware.cron4j.ProcessTask;

import javax.servlet.http.HttpServletRequest;

/**
 * 应用配置
 *
 * @author zhangchuang
 */
public class AppConfig extends JFinalConfig {
    Prop configProp = PropKit.use("config.properties");
    Prop fileProp = PropKit.use("file.properties");

    @Override
    public void configConstant(Constants me) {
        // 开发模式
        me.setDevMode(configProp.getBoolean("devMode"));
        // 上传下载
        me.setBaseUploadPath(fileProp.get("upload"));
        me.setMaxPostSize(fileProp.getInt("maxPostSize"));
        me.setBaseDownloadPath(fileProp.get("download"));

        // 视图
        me.setViewType(ViewType.FREE_MARKER);
        me.setError403View(Constant.VIEW_PATH + "common/403.ftl");
        me.setError404View(Constant.VIEW_PATH + "common/404.ftl");
        me.setError500View(Constant.VIEW_PATH + "common/500.ftl");

        // json (jfinaljson 和 fastjson 混合)
        me.setJsonFactory(MixedJsonFactory.me());  // toJson 用 JfinalJson , Parse 用 Fastjson, model 转json 使用数据库字段名
        //me.setJsonFactory(FastJsonFactory.me()); // 使用 Fastjson ,Model 转 json 使用 驼峰命名， 依赖 getter 方法
        me.setJsonDatePattern("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/login", LoginController.class, Constant.VIEW_PATH); // 登录
        me.add("/dashboard", MainController.class, Constant.VIEW_PATH);   // 控制面板
        me.add("/utils", UtilsController.class, Constant.VIEW_PATH); // 页面工具

        me.add(new SystemRoute());
    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {
        Prop jdbcProp = PropKit.use("jdbc.properties");

        // 数据库
        DruidPlugin druidPlugin = new DruidPlugin(jdbcProp.get("jdbc.url"), jdbcProp.get("jdbc.user"), jdbcProp.get("jdbc.password"), jdbcProp.get("jdbc.driver"));
        druidPlugin.addFilter(new StatFilter());
        WallFilter wall = new WallFilter();
        wall.setDbType(jdbcProp.get("jdbc.dbType"));
        druidPlugin.addFilter(wall);
        me.add(druidPlugin);
        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        activeRecordPlugin.setDialect(new OracleDialect());
        activeRecordPlugin.setContainerFactory(new CaseInsensitiveContainerFactory()); // 表字段大小写不敏感
        activeRecordPlugin.setShowSql(configProp.getBoolean("devMode"));
        SystemModelMapping.mapping(activeRecordPlugin);                                // system 模块 表映射

        me.add(activeRecordPlugin);

        // 定时任务插件
        Cron4jPlugin cron4jPlugin = new Cron4jPlugin("task.properties");
        me.add(cron4jPlugin);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.addGlobalActionInterceptor(new ExceptionInterceptor());   // 异常，访问日志
        me.addGlobalActionInterceptor(new LoginInterceptor());       // 登录
        me.addGlobalActionInterceptor(new PermissionInterceptor());  // 权限
    }

    @Override
    public void configHandler(Handlers me) {
        // 跳过处理 ws 请求
        me.add(new UrlSkipHandler("^/ws-server", false));
        // 视图中 添加context路径
        me.add(new ContextPathHandler("ctx"));
        // druid 监控 （只允许admin查看)
        DruidStatViewHandler dvh = new DruidStatViewHandler("/druidWeb", new IDruidStatViewAuth() {
            public boolean isPermitted(HttpServletRequest request) {
                SysUser sysUser = (SysUser) request.getSession().getAttribute(Constant.SYS_USER);
                if (sysUser == null) {
                    return false;
                }
                return "admin".equals(sysUser.getUsername());
            }
        });
        me.add(dvh);
    }

    @Override
    public void afterJFinalStart() {
        super.afterJFinalStart();
        Record record = Db.findFirst("select count(1) c from sys_visit_log");
        System.out.println("---------------------------------: visitLog count: "+record.get("c"));
    }

    @Override
    public void beforeJFinalStop() {
        super.beforeJFinalStop();
    }
}
