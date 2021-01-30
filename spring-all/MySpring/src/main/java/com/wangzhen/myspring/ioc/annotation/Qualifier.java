package com.wangzhen.myspring.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:  一般和 @Autowired 注解一起使用，用来指定具体的实现
 * Datetime:    2020/10/25   9:21 下午
 * Author:   王震
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Qualifier {
    String value() default "";
}