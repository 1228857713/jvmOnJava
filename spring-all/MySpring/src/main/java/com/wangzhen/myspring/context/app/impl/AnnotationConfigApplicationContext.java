package com.wangzhen.myspring.context.app.impl;

import com.wangzhen.myspring.bean.beandefinition.BeanDefinition;
import com.wangzhen.myspring.bean.beandefinition.BeanDefinitionRegistry;
import com.wangzhen.myspring.bean.factory.BeanFactory;
import com.wangzhen.myspring.bean.factory.impl.DefaultBeanFactory;
import com.wangzhen.myspring.context.reader.BeanDefinitionReader;
import com.wangzhen.myspring.context.reader.impl.AnnotationBeanDefinitionReader;
import com.wangzhen.myspring.context.scanner.ClassPathBeanDefinitionScanner;

import java.util.Map;

/**
 * Description:
 * Datetime:    2020/11/2   16:29
 * Author:   王震
 */
public class AnnotationConfigApplicationContext extends AbstractApplicationContext {



    //
    private AnnotationBeanDefinitionReader annotationBeanDefinitionReader;

    private ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner;

    public AnnotationConfigApplicationContext() {
        beanFactory = new DefaultBeanFactory();
       // this.annotationBeanDefinitionReader = new AnnotationBeanDefinitionReader(this);
        this.classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner((BeanDefinitionRegistry) beanFactory);

    }

    public AnnotationConfigApplicationContext(Class<?>... configClass) {
        this();
        refresh(configClass);

    }

    public void refresh(Class<?>... configClass){
        classPathBeanDefinitionScanner.scan(configClass);
    }

    @Override
    public String[] getBeanNameForType(Class<?> tClass) {
        return new String[0];
    }

    @Override
    public Map<String, Object> getBeansForType(Class<?> clazz) {
        return null;
    }

    @Override
    public Class getType(String beanName) {
        return null;
    }
}