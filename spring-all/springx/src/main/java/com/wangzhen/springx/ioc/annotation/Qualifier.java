package com.wangzhen.springx.ioc.annotation;

import java.lang.annotation.*;

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