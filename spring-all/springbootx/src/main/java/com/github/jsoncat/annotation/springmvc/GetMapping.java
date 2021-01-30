package com.github.jsoncat.annotation.springmvc;

import java.lang.annotation.*;

/**
 * @author shuang.kou
 * @createTime 2020年09月24日 14:46:00
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GetMapping {
    String value() default "";
}
