package com.wangzhen.dubbox.spring;

import com.wangzhen.dubbox.annotation.RpcScan;
import com.wangzhen.dubbox.annotation.RpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.stereotype.Component;

import java.rmi.registry.Registry;

/**
 * Description:
 * Datetime:    2020/11/23   15:01
 * Author:   王震
 */
@Slf4j
public class CustomScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {
    private static final String SPRING_BEAN_BASE_PACKAGE = "com.wangzhen.dubbox";
    private static final String BASE_PACKAGE_ATTRIBUTE_NAME = "basePackage";
   private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(RpcScan.class.getName()));
        String[] rpcScanBasePackages = new String[0];
        if (annotationAttributes != null) {
            // get the value of the basePackage property
            rpcScanBasePackages = annotationAttributes.getStringArray(BASE_PACKAGE_ATTRIBUTE_NAME);
        }
        if (rpcScanBasePackages.length == 0) {
            rpcScanBasePackages = new String[]{((StandardAnnotationMetadata) importingClassMetadata).getIntrospectedClass().getPackage().getName()};
        }

        CustomScanner rpcServiceScanner = new CustomScanner(registry, RpcService.class);
        CustomScanner springBeanScanner = new CustomScanner(registry, Component.class);
        if(resourceLoader!=null){
            rpcServiceScanner.setResourceLoader(resourceLoader);
            rpcServiceScanner.setResourceLoader(resourceLoader);
        }
        int rpcServiceAmount = rpcServiceScanner.scan(rpcScanBasePackages);
        log.info("rpcServiceScanner扫描的数量 [{}]", rpcServiceAmount);
        int springBeanAmount = springBeanScanner.scan(SPRING_BEAN_BASE_PACKAGE);
        log.info("springBeanScanner扫描的数量 [{}]", springBeanAmount);
    }


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}