package com.mycurdpro.common.annotation;
import java.lang.annotation.*;

/**
 * 通知类型编码
 * @author zhangchuang
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoticeCode {
    String value() default "";                 // 通知类型编码，多个之间用逗号分隔
}
