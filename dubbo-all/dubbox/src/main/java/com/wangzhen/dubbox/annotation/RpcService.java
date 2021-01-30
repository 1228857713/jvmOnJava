package com.wangzhen.dubbox.annotation;


import java.lang.annotation.*;

/**
 * RPC service annotation, marked on the service implementation class
 *
 * @author shuang.kou
 * @createTime 2020年07月21日 13:11:00
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface RpcService {

    /**
     * Service version, default value is empty string
     */
    String version() default "";

    /**
     * Service group, default value is empty string
     */
    String group() default "";

}
