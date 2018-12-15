package com.mycurdpro.common.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池容器，供其它工具使用
 *
 * @author chuang
 * @date 2018/9/10
 */
public class ExecutorServiceUtils {
    private final static Logger LOG = LoggerFactory.getLogger(ExecutorServiceUtils.class);

    // 固定大小线程池
    public static ExecutorService pool;

    static {
        LOG.info("----- 初始化线程池 -----");
        pool = new ThreadPoolExecutor(5, 10,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().setNameFormat("my_curd_pro-%d").setDaemon(true).build());
    }
}
