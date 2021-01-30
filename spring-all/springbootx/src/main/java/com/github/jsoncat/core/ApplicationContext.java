package com.github.jsoncat.core;

import com.github.jsoncat.annotation.boot.ComponentScan;

import java.util.Objects;

/**
 * Description:
 * Datetime:    2020/11/13   4:31 下午
 * Author:   王震
 */
public final class ApplicationContext {
    private static final ApplicationContext APPLICATION_CONTEXT = new ApplicationContext();

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    public void run(Class<?> applicationClass){

        // 打到注解类上面的 包名
        String []packages = getPackageNames(applicationClass);


    }

    private static String[] getPackageNames(Class<?> applicationClass){
        ComponentScan componentScan = applicationClass.getAnnotation(ComponentScan.class);
        return !Objects.isNull(componentScan) ? componentScan.value() : new String[]{applicationClass.getPackage().getName()};


    }

}
