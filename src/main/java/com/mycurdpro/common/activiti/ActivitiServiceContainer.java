package com.mycurdpro.common.activiti;

import org.activiti.engine.*;

public class ActivitiServiceContainer {
    public  static ProcessEngine processEngine;

    public static RepositoryService repositoryService;
    public static RuntimeService runtimeService;
    public static TaskService taskService;
    public static HistoryService historyService;
    public static IdentityService identityService;
    public static FormService formService;

    public static void  init(ProcessEngine processEngine){
        processEngine = processEngine;
        repositoryService = processEngine.getRepositoryService();
        runtimeService = processEngine.getRuntimeService();
        taskService = processEngine.getTaskService();
        historyService = processEngine.getHistoryService();
        identityService = processEngine.getIdentityService();
        formService = processEngine.getFormService();
    }
}
