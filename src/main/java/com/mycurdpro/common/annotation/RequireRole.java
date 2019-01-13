package com.mycurdpro.common.annotation;

import java.lang.annotation.*;

/**
 * controller method 运行必须角色 注解
 *
 * @author zhangchuang
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireRole {
    String value() default "";                 // 角色编码，逗号分隔

    Relation relation() default Relation.OR;   //  角色之间关系，and  或者  or

    enum Relation {
        AND, OR
    }
}




