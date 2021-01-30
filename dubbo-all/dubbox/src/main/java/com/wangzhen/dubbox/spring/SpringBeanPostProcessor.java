package com.wangzhen.dubbox.spring;

import com.wangzhen.dubbox.annotation.RpcReference;
import com.wangzhen.dubbox.annotation.RpcService;
import com.wangzhen.dubbox.common.entity.RpcServiceProperties;
import com.wangzhen.dubbox.provider.ServiceProvider;
import com.wangzhen.dubbox.proxy.RpcClientProxy;
import com.wangzhen.dubbox.remoting.transport.RpcRequestTransport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ServiceLoader;

/**
 * Description: bean 的后置处理器，会在bean初始化之前和初始化之后对 bean 进行设置
 * Datetime:    2020/11/23   15:39
 * Author:   王震
 */
@Slf4j
@Component
public class SpringBeanPostProcessor implements BeanPostProcessor {

    private final ServiceProvider serviceProvider;
    private final RpcRequestTransport RpcRequestTransport;

    public SpringBeanPostProcessor(ServiceProvider serviceProvider, RpcRequestTransport RpcRequestTransport) {
        this.serviceProvider = serviceProvider;
        this.RpcRequestTransport = RpcRequestTransport;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAnnotationPresent(RpcService.class)){
            log.info("{}类上面有{}注解",beanName,RpcService.class.getCanonicalName());
            RpcService rpcServiceAnnotaion = bean.getClass().getAnnotation(RpcService.class);
            RpcServiceProperties build = RpcServiceProperties.builder().group(rpcServiceAnnotaion.group()).version(rpcServiceAnnotaion.version()).build();
            serviceProvider.publishService(bean,build);
        }
        return bean;

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 获取对象上的所有属性
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        if(declaredFields.length>0){
            for (Field declaredField : declaredFields) {
                // 查看属性上是否有 RpcReference 这个注解
                if (declaredField.isAnnotationPresent(RpcReference.class)) {
                    RpcReference annotation = declaredField.getAnnotation(RpcReference.class);
                    RpcServiceProperties rpcServiceProperties = RpcServiceProperties.builder().group(annotation.group()).version(annotation.version()).build();
                    RpcClientProxy rpcClientProxy = new RpcClientProxy(RpcRequestTransport, rpcServiceProperties);
                    // 通过 jdk 的动态代理得到 该对象的代理对象，为 RpcReference 注入该对象
                    Object proxy = rpcClientProxy.getProxy(declaredField.getType());
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(bean,proxy);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return bean;
    }
}