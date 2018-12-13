package com.mycurdpro.common.utils.guava;


/**
 * 缓存实例容器
 *
 * @author chuang
 * @date 2018/9/3
 */
public class CacheContainer {
    private static BaseCache loginRetryLimitCache = null;

    private CacheContainer() {
    }


    /**
     * 获得单例 登录错误 缓存
     *
     * @return 登录错误缓存
     */
    public static BaseCache getLoginRetryLimitCache() {
        if (loginRetryLimitCache == null) {
            synchronized (LoginRetryLimitCache.class) {
                if (loginRetryLimitCache == null) {
                    loginRetryLimitCache = new LoginRetryLimitCache();
                }
            }
        }
        return loginRetryLimitCache;
    }
}
