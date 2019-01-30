package com.mycurdpro.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 前台 配色拦截器  ( 非业务，纯玩 :) )
 * @author zhangchuang
 */
public class ThemeInterceptor  implements Interceptor {
    private final static Logger LOG = LoggerFactory.getLogger(ThemeInterceptor.class);

    // 可以根据 人眼习惯 做 配色
    private final static Map<Integer,String> dayColorMap = new HashMap<Integer,String>(){{
        put(1,"tumblr");
        put(2,"tumblr");
        put(3,"tumblr");
        put(4,"tumblr");
        put(5,"tumblr");
        put(6,"wordpress");
        put(7,"wordpress");
        put(8,"facebook");
        put(9,"default");
        put(10,"default");
        put(11,"green");
        put(12,"green");
        put(13,"wechat");
        put(14,"default");
        put(15,"youtube");
        put(16,"wechat");
        put(17,"green");
        put(18,"wordpress");
        put(19,"wordpress");
        put(20,"tumblr");
        put(21,"tumblr");
        put(22,"tumblr");
        put(23,"tumblr");
        put(24,"tumblr");
    }};

    @Override
    public void intercept(Invocation invocation) {
        DateTime dateTime = new DateTime();
        LOG.info("hour of day:{}", dateTime.getHourOfDay());
        LOG.info("color:{}",dayColorMap.get(dateTime.getHourOfDay()));
        invocation.getController().setAttr("color",dayColorMap.get(dateTime.getHourOfDay()));
        invocation.invoke();
    }
}
