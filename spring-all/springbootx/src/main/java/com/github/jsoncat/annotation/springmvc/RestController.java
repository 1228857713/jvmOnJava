package com.github.jsoncat.annotation.springmvc;


import com.github.jsoncat.annotation.ioc.Component;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RestController {
    String value() default "";
}
