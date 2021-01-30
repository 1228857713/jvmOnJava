package com.github.jsoncat.annotation.ioc;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String name() default "";
}