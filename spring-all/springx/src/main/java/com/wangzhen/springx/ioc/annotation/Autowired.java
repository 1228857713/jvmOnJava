package com.wangzhen.springx.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 自动装配注解 默认按照名字来装配，
 *              如果需要其他方式，那么需要 @Qualifier 配合使用
 * Datetime:    2020/10/25   4:49 下午
 * Author:   王震
 */

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME) // 该注解在运行时 生效
public @interface Autowired {
}
