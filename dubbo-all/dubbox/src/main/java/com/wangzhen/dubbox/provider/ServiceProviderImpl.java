package com.wangzhen.dubbox.provider;

import com.wangzhen.dubbox.common.entity.RpcServiceProperties;
import com.wangzhen.dubbox.common.enums.RpcErrorMessageEnum;
import com.wangzhen.dubbox.common.exception.RpcException;
import com.wangzhen.dubbox.common.extension.ExtensionLoader;
import com.wangzhen.dubbox.registry.ServiceRegistry;
import com.wangzhen.dubbox.remoting.transport.netty.server.NettyRpcServer;
import lombok.extern.slf4j.Slf4j;


import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: 服务提供注册的实现类
 * Datetime:    2020/11/26   11:00
 * Author:   王震
 */
@Slf4j
public class ServiceProviderImpl implements ServiceProvider {

    private final Map<String, Object> serviceMap;
    private final Set<String> registeredService;
    private final ServiceRegistry serviceRegistry;



    public ServiceProviderImpl() {
        serviceMap = new ConcurrentHashMap<>();
        registeredService =new  ConcurrentHashMap().newKeySet();
        serviceRegistry= ExtensionLoader.getExtensionLoader(ServiceRegistry.class).getExtension("zk");
    }

    @Override
    public void addService(Object service, Class<?> serviceClass, RpcServiceProperties rpcServiceProperties) {
            String rpcName = rpcServiceProperties.toRpcServiceName();
            if(registeredService.contains(rpcName)){
                log.info("{} 已经被注册过了，不在注册",rpcName);
                return ;
            }
            registeredService.add(rpcName);
            serviceMap.put(rpcName,service);
            log.info("Add service: {} and interfaces:{}", rpcName, service.getClass().getInterfaces());

    }

    @Override
    public Object getService(RpcServiceProperties rpcServiceProperties) {
        Object service = serviceMap.get(rpcServiceProperties.toRpcServiceName());
        if (null == service) {
            throw new RpcException(RpcErrorMessageEnum.SERVICE_CAN_NOT_BE_FOUND);
        }
        return service;
    }

    @Override
    public void publishService(Object service, RpcServiceProperties rpcServiceProperties) {
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            Class<?> serviceInterface = service.getClass().getInterfaces()[0];
            String serviceName = serviceInterface.getCanonicalName();
            rpcServiceProperties.setServiceName(serviceName);
            this.addService(service,serviceInterface,rpcServiceProperties);
            serviceRegistry.registerService(rpcServiceProperties.toRpcServiceName(),new InetSocketAddress(hostAddress, NettyRpcServer.PORT));

        } catch (UnknownHostException e) {
            log.error(e.toString());
        }
    }

    @Override
    public void publishService(Object service) {
        this.publishService(service, RpcServiceProperties.builder().group("").version("").build());
    }
}