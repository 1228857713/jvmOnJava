package com.wangzhen.springx.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 扫描注解，制定springx 应该扫描哪些注解
 * Datetime:    2020/10/24   9:21 下午
 * Author:   王震
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentScan {
    //扫描包的位置
    String [] value() default {};
    //扫描包的位置
    String[] basePackages() default {};
}
