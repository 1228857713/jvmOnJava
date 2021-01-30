package com.wangzhen.springx.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:
 * Datetime:    2020/10/25   9:24 下午
 * Author:   王震
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
    //controller 的值
    String value() default "";
}
