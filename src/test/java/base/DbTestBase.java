package base;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;


public class DbTestBase {

    static final Prop configProp = PropKit.use("jdbc.properties");
    public static   void  dbInit(){
        DruidPlugin dp = new DruidPlugin(configProp.get("jdbc.url"), configProp.get("jdbc.user"), configProp.get("jdbc.password"));
        dp.setDriverClass(configProp.get("jdbc.driver"));
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.setDialect(new OracleDialect());
        arp.setContainerFactory(new CaseInsensitiveContainerFactory());
        arp.setShowSql(true);
        //SysMappingKit.mapping(arp);
        dp.start();
        arp.start();
    }

    public static DataSource getDataSource() {
        PropKit.use("jdbc.properties");
        DruidPlugin dp = new DruidPlugin(configProp.get("jdbc.url"), configProp.get("jdbc.user"), configProp.get("jdbc.password"),configProp.get("jdbc.driver"));
        dp.start();
        return dp.getDataSource();
    }

    public static void main(String[] args) {


        // base model 所使用的包名
        String baseModelPkg = "com.mycurdpro.system.model.base";
        // base model 文件保存路径
        String baseModelDir = PathKit.getWebRootPath() + "/src/main/java/com/mycurdpro/system/model/base";

        // model 所使用的包名
        String modelPkg = "com.mycurdpro.system.model";
        // model 文件保存路径
        String modelDir = baseModelDir + "/..";

        Generator gernerator = new Generator(getDataSource(), baseModelPkg, baseModelDir, modelPkg, modelDir);
        gernerator.setDialect(new OracleDialect());
        gernerator.setGenerateChainSetter(true);
        gernerator.setGenerateDaoInModel(true);

        gernerator.generate();

    }
}
