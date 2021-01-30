package com.wangzhen.myspring.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 实现bean 注解
 * Datetime:    2020/10/24   8:44 下午
 * Author:   王震
 */
@Target(ElementType.TYPE) // 表明该注解用于类或者接口，或者枚举类型
@Retention(RetentionPolicy.RUNTIME) // 该注解在运行时 生效
public @interface Component {
    String value() default "";
}
