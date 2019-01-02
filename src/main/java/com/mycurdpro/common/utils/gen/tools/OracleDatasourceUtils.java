package com.mycurdpro.common.utils.gen.tools;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;

/**
 * oracle 数据库获得数据源
 */
public class OracleDatasourceUtils {
    /**
     * 获得数据库数据源，用于代码生成器
     *
     * @return 数据源
     */
    public static DataSource getDataSource() {
        Prop configProp = PropKit.use("jdbc.properties");
        DruidPlugin dp = new DruidPlugin(configProp.get("jdbc.url"), configProp.get("jdbc.user"),
                configProp.get("jdbc.password"), configProp.get("jdbc.driver"));
        dp.setConnectionProperties("remarksReporting=true");  // oracle 特色， 为了获得列注释
        dp.start();
        return dp.getDataSource();
    }
}
