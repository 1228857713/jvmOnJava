package com.github.jsoncat.annotation.boot;

import java.lang.annotation.*;

/**
 * Description:
 * Datetime:    2020/11/13   4:36 下午
 * Author:   王震
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ComponentScan
public @interface SpringBootApplication {
}
