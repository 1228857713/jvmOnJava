package com.wangzhen.dubbox.provider;

import com.wangzhen.dubbox.common.entity.RpcServiceProperties;

/**
 * Description:
 * Datetime:    2020/11/23   15:58
 * Author:   王震
 */
public interface ServiceProvider {

    void addService(Object service, Class<?> serviceClass, RpcServiceProperties rpcServiceProperties);


    Object getService(RpcServiceProperties rpcServiceProperties);


    void publishService(Object service, RpcServiceProperties rpcServiceProperties);


    void publishService(Object service);
}