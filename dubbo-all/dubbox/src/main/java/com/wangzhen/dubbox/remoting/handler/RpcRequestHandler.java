package com.wangzhen.dubbox.remoting.handler;


import com.wangzhen.dubbox.common.exception.RpcException;
import com.wangzhen.dubbox.common.factory.SingletonFactory;
import com.wangzhen.dubbox.provider.ServiceProvider;
import com.wangzhen.dubbox.provider.ServiceProviderImpl;
import com.wangzhen.dubbox.remoting.dto.RpcRequest;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;


/**
 * Description:
 * Datetime:    2020/11/27   10:33
 * Author:   王震
 */


@Slf4j
public class RpcRequestHandler {
    private final ServiceProvider serviceProvider;

    public RpcRequestHandler() {
        this.serviceProvider = SingletonFactory.getInstance(ServiceProviderImpl.class);

    }

    public Object handle(RpcRequest rpcRequest) {
        Object service = serviceProvider.getService(rpcRequest.toRpcProperties());
        return invokeTargetMethod(rpcRequest, service);
    }

    private Object invokeTargetMethod(RpcRequest rpcRequest, Object service) {
        Object result;
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            result= method.invoke(service, rpcRequest.getParameters());
            log.info("service:[{}] successful invoke method:[{}]", rpcRequest.getInterfaceName(), rpcRequest.getMethodName());
        } catch (Exception e) {
            throw new RpcException(e.getMessage(), e);
        }
        return result;

    }
}