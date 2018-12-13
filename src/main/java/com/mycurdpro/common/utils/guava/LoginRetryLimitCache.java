package com.mycurdpro.common.utils.guava;

import com.jfinal.kit.PropKit;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 登录密码验证错误 缓存
 *
 * @author chuang
 */
public class LoginRetryLimitCache extends BaseCache<String, AtomicInteger> {

    private final static Long LOCK_TIME = PropKit.use("config.properties").getLong("lockTime");

    public LoginRetryLimitCache() {
        super(LOCK_TIME, TimeUnit.MINUTES);
    }

    @Override
    protected AtomicInteger loadData(String s) {
        return new AtomicInteger();
    }
}
