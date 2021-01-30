package com.github.jsoncat.annotation.boot;

import java.lang.annotation.*;

/**
 * Description:
 * Datetime:    2020/11/13   4:35 下午
 * Author:   王震
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ComponentScan {
    String[] value() default {};
}
