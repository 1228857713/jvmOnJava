package com.wangzhen.springx.core;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 类扫描器
 * Datetime:    2020/10/25   12:10 上午
 * Author:   王震
 */
public interface ClassScanner {
    List<Class<?>> classList = new ArrayList<>();
    // 扫描包名下所有的类
    public List<Class<?>> getClassListByPackage(String packageName);

    // 获取指定包下所有包含制定注解的类
    public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass);

    public List<Class<?>> getClassListBySuper(String packageName,Class<?> superClass);
}
