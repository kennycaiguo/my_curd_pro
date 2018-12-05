package com.mycurdpro.common.utils;

import com.jfinal.plugin.activerecord.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Jfinal model 转 bean 工具 ，M 为 入参（Model 或 其字类)，B 为方法返回值(Bean)
 * easypoi excel 等使用
 * @author  zhangchuang
 */
public class ModelBeanUtils<M,B> {

    private final static Logger LOG = LoggerFactory.getLogger(ModelBeanUtils.class);


    /**
     * model 转 bean
     * @param model     model或 其字类
     * @param beanClass bean class
     * @param mapping   model 字段 到 bean 字段的映射
     * @return bean 不为 null
     */
    public B modelToBean(@NotNull M model, @NotNull  Class<B> beanClass, @NotNull Map<String,String> mapping){

        // 初始化
        Model  m = (Model) model;
        B o;
        try {
            o = beanClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOG.error(e.getMessage(),e);
            throw new RuntimeException(" bean 初始化失败");
        }

        // 字段设置值
        Field field;
        for(Map.Entry<String,String> entry : mapping.entrySet()){
            try {
                field = beanClass.getDeclaredField(entry.getValue());
                field.setAccessible(true);
                field.set(o,m.get(entry.getKey()));
            } catch (NoSuchFieldException | IllegalAccessException  e) {
                LOG.error(e.getMessage(),e);
                throw new RuntimeException(entry.getValue()+" field 设值失败");
            }
        }
        return o;
    }

    /**
     * model list  转换为 Bean list
     * @param models    model或其子类
     * @param beanClass bean class
     * @param mapping   model 字段到 bean 字段的映射
     * @return  bean list, 不为null,可为空数组
     */
    public  List<B> modelsToBeans(@NotNull List<M> models, @NotNull Class<B> beanClass,@NotNull Map<String,String> mapping){
        List<B> list = new ArrayList<>();
        if(models.size()==0){
            return list;
        }
        for(M model : models){
            B t = modelToBean(model,beanClass,mapping);
            list.add(t);
        }
        return list;
    }
}
