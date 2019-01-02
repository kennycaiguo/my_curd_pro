package com.mycurdpro.system.controller;

import com.google.common.base.Throwables;
import com.google.common.collect.Collections2;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.utils.ReflectionUtils;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.vo.Cron4jTaskIntro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 定时任务
 */
public class SysTaskController extends BaseController {
    private  final static Logger LOG = LoggerFactory.getLogger(SysTaskController.class);

    private final static Prop prop = PropKit.use("task.properties");
    public void index(){
        render("system/sysTask.ftl");
    }

    public void query(){
        List<Cron4jTaskIntro> data  = new ArrayList<>();
        initData(data);

        // 查询条件
        String name = getPara("name");
        String className = getPara("class");
        String daemon = getPara("daemon");
        String enable = getPara("enable");

        Collection<Cron4jTaskIntro>  result = null;
        if(StringUtils.notEmpty(name)){
           result = Collections2.filter(data, x -> x.getName().contains(name));
        }
        if(StringUtils.notEmpty(className)){
            result = Collections2.filter(data, x -> x.getClassName().contains(className));
        }
        if(StringUtils.notEmpty(daemon)){
            result = Collections2.filter(data, x -> x.getDaemon().equals(daemon));
        }
        if(StringUtils.notEmpty(enable)){
            result = Collections2.filter(data, x -> x.getEnable().contains(enable));
        }
        if(result ==null){
            result = new ArrayList<>();
        }

        renderDatagrid((List<Cron4jTaskIntro>) result,result.size());
    }

    /**
     * 运行一次  (同步)
     */
    public void runOnce(){
        String className = getPara("className");
        if(StringUtils.isEmpty(className)){
            renderFail("className 参数不可为空");
            return;
        }
        synchronized (this){
            try{
                ReflectionUtils.runMethod(className,"run");
            }catch (Exception e){
                LOG.error(e.getMessage(),e);
                renderFail("运行异常，原因:"+ Throwables.getStackTraceAsString(e));
                return;
            }
        }
        renderSuccess("运行成功");
    }



    private static void initData(List<Cron4jTaskIntro> data){
        String taskNames = prop.get("cron4j");
        if(StringUtils.isEmpty(taskNames)){
            return;
        }
        String[] taskNameAry = taskNames.split(",");
        for(String taskName : taskNameAry){
            String cron = prop.get(taskName+".cron");
            String className = prop.get(taskName+".class");
            String daemon = prop.get(taskName+".daemon");
            String enable = prop.get(taskName+".enable");
            Cron4jTaskIntro intro  = new Cron4jTaskIntro();
            intro.setName(taskName);
            intro.setClassName(cron);
            intro.setClassName(className);
            intro.setDaemon(daemon);
            intro.setEnable(enable);
            data.add(intro);
        }
    }
}