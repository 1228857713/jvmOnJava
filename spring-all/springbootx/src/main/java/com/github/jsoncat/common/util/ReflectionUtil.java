package com.github.jsoncat.common.util;

import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Description:
 * Datetime:    2020/11/17   10:54
 * Author:   王震
 */
public class ReflectionUtil {

   static Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     *
     * @param 需要扫描的包名
     * @param 扫描的注解
     * @return
     */
    public static Set<Class<?>> scanAnnotatedClass(String []packageNames, Class<? extends Annotation> annotation){

        Reflections reflections = new Reflections(packageNames, new TypeAnnotationsScanner());
        // 扫描包名下所有符合注解的包名类
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(annotation, true);
        logger.info("The number of class Annotated with @" + annotation.getSimpleName() + ":[{}]", classes.size());
        return classes;

    }
}
