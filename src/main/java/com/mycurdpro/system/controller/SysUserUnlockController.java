package com.mycurdpro.system.controller;

import com.google.common.base.Joiner;
import com.jfinal.aop.Before;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.utils.guava.BaseCache;
import com.mycurdpro.common.utils.guava.CacheContainer;
import com.mycurdpro.common.validator.IdsRequired;
import com.mycurdpro.system.model.SysUser;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 缓存中账号解锁
 */
public class SysUserUnlockController extends BaseController {

    // 密码错误尝试次数
    private final  static int loginRetryLimitTime = PropKit.use("config.properties").getInt("loginRetryLimitTime");

    public void index(){
        render("system/sysUserUnlock.ftl");
    }

    public void  query(){
        Map<String, AtomicInteger> cacheAsMap = CacheContainer.getLoginRetryLimitCache().getCache().asMap();
        Set<String> userNameSet = new LinkedHashSet<>();
        cacheAsMap.forEach((K,V)->{
            System.err.println(K);
            if(V.get()>=loginRetryLimitTime){
                userNameSet.add(K);
            }
        });
        List<SysUser> sysUsers = new ArrayList<>();
        String ids  = "'"+ Joiner.on("','").join(userNameSet)+"'";
        List<Record> records = Db.find("select * from sys_user where username in ("+ids+")");
        renderDatagrid(records);
    }


    /**
     * 解锁
     */
    @Before(Tx.class)
    public void unlockAction(){
        String usernames = getPara("usernames");
        if(StringUtils.isEmpty(usernames)){
            renderFail("参数不可为空");
            return;
        }

        String[] usernameAry = usernames.split(",");
        BaseCache<String,AtomicInteger> baseCache = CacheContainer.getLoginRetryLimitCache();
        for(String username:usernameAry){
             baseCache.put(username,new AtomicInteger());
        }

        addServiceLog(usernames+" 账号解锁");

        renderSuccess("解锁成功");
    }
}
