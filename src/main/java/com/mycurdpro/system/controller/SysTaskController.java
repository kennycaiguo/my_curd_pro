package com.mycurdpro.system.controller;

import com.google.common.base.Throwables;
import com.google.common.collect.Collections2;
import com.jfinal.aop.Before;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.cron4j.ITask;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.SearchSql;
import com.mycurdpro.common.utils.ReflectionUtils;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.validator.IdsRequired;
import com.mycurdpro.system.model.SysTaskLog;
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

    public SysTaskController() {
        //  每一次url 方法访问 都会重新 创建 controller 对象，忽然感觉 jfinal 很恐怖  :(,
        System.out.println("init  ......................");
    }

    private final static Prop prop = PropKit.use("task.properties");
    public void index(){
        render("system/sysTask.ftl");
    }

    public void query(){
        List<Cron4jTaskIntro> data  = new ArrayList<>();
        initData(data);

        // 查询条件
        String name = getPara("extra_name");
        String className = getPara("extra_class");
        String daemon = getPara("extra_daemon");
        String enable = getPara("extra_enable");

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
            renderDatagrid(data,data.size());
        }else{
            renderDatagrid(result,result.size());
        }
    }

    /**
     * 运行一次  (同步)
     */
    public  void runOnce(){
        String className = getPara("className");
        if(StringUtils.isEmpty(className)){
            renderFail("className 参数不可为空");
            return;
        }

        // 每一次url 访问 都会重新 创建 controller 对象，忽然感觉 jfinal 很恐怖  :(,
        // 此处只好如此加锁
        synchronized (SysTaskController.class){
            try{
                // 通过反射运行
                Class classObject = Class.forName(className);
                Object instance = classObject.newInstance();
                if(instance instanceof  ITask){
                    ReflectionUtils.runMethod(instance,"run");
                    ReflectionUtils.runMethod(instance,"stop");
                }else{
                    ReflectionUtils.runMethod(instance,"run");
                }
            }catch (Exception e){
                LOG.error(e.getMessage(),e);
                renderFail("运行异常，原因:"+ Throwables.getStackTraceAsString(e));
                return;
            }
        }

        renderSuccess("运行成功");
    }


    /**
     * 运行日志
     */
    public  void  taskLog(){
        setAttr("className",getPara("className"));
        render("system/sysTaskLog.ftl");
    }
    @Before(SearchSql.class)
    public void taskLogQuery(){
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysTaskLog> sysTaskLogPage = SysTaskLog.dao.page(pageNumber,pageSize,where);
        renderDatagrid(sysTaskLogPage);
    }
    @Before(IdsRequired.class)
    public void deleteTaskLogAction(){
        String ids = getPara("ids").replaceAll(",","','");
        String sql = "delete from sys_task_log where id in ('"+ids+"')";
        Db.update(sql);
        renderSuccess(Constant.DELETE_SUCCESS);
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
            intro.setCron(cron);
            intro.setClassName(className);
            intro.setDaemon(daemon);
            intro.setEnable(enable);

            data.add(intro);
        }
    }
}