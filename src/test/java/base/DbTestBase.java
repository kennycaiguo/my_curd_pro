package base;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;

import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.mycurdpro.system.SystemModelMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class DbTestBase {

    private final static Logger LOG = LoggerFactory.getLogger(DbTestBase.class);
    static final Prop configProp = PropKit.use("jdbc.properties");
    public static void  dbInit(){
        DruidPlugin dp = new DruidPlugin(configProp.get("jdbc.url"), configProp.get("jdbc.user"), configProp.get("jdbc.password"),configProp.get("jdbc.driver"));
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.setDialect(new OracleDialect());
        arp.setContainerFactory(new CaseInsensitiveContainerFactory());
        arp.setShowSql(true);
        SystemModelMapping.mapping(arp);
        dp.start();
        arp.start();
    }

}
