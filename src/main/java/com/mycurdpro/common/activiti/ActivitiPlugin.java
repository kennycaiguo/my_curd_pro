package com.mycurdpro.common.activiti;

import com.jfinal.plugin.IPlugin;
import com.jfinal.plugin.activerecord.DbKit;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * activiti 工作流插件
 */
public class ActivitiPlugin implements IPlugin {
    private final static Logger LOG = LoggerFactory.getLogger(ActivitiPlugin.class);
    private boolean isStarted = false;

    @Override
    public boolean start() {
        try {
            createProcessEngine();
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
        }
        return true;
    }

    @Override
    public boolean stop() {
        ProcessEngines.destroy();
        isStarted = false;
        return true;
    }

    private Boolean createProcessEngine() throws Exception {
        if (isStarted) {
            return true;
        }
        StandaloneProcessEngineConfiguration conf = (StandaloneProcessEngineConfiguration) ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        conf.setDataSource(DbKit.getConfig().getDataSource());
        conf.setEnableDatabaseEventLogging(false);
        conf.setDatabaseSchemaUpdate(ProcessEngineConfigurationImpl.DB_SCHEMA_UPDATE_TRUE);//更新
        // conf.setDatabaseSchemaUpdate(ProcessEngineConfigurationImpl.DB_SCHEMA_UPDATE_DROP_CREATE);//重置数据库!!!调试用!!!请勿打开!!!
        conf.setDbHistoryUsed(true);
        // conf.setTransactionsExternallyManaged(true); // 使用托管事务工厂
        conf.setTransactionFactory(new ActivitiTransactionFactory());
        UuidGenerator uuidG = new UuidGenerator();
        conf.setIdGenerator(uuidG);

        ActivitiServiceContainer.init(conf.buildProcessEngine());

        isStarted = true;
        //开启流程引擎
        LOG.info("启动流程引擎");
        return isStarted;
    }
}