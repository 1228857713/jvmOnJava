package com.wangzhen.dubbox.loadbalance;

import java.util.List;

/**
 * Description:
 * Datetime:    2020/12/3   15:37
 * Author:   王震
 */
public abstract class AbstractLoadBalance implements LoadBalance{
    @Override
    public String selectServiceAddress(List<String> serviceAddresses, String rpcServiceName) {
        return null;
    }
}