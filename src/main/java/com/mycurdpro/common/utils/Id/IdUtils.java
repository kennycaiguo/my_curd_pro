package com.mycurdpro.common.utils.Id;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

/**
 * 生成唯一性ID算法的工具类.
 */
public class IdUtils {
    private final static  Prop prop = PropKit.use("config.properties");
    private final  static SnowflakeIdWorker idWorker = new SnowflakeIdWorker(prop.getInt("workerId"), prop.getInt("datacenterId"));

    /**
     * 18 位数字型 字符串
     *
     * @return
     */
    public static String id() {
        return String.valueOf(idWorker.nextId());
    }

    /**
     * 18 位 Long 类型
     *
     * @return
     */
    public static Long idn() {
        return idWorker.nextId();
    }
}
