package com.wangzhen.dubbox.loadbalance.loadbalancer;

import com.wangzhen.dubbox.loadbalance.LoadBalance;

import java.util.List;
import java.util.Random;

/**
 * Description: 随机的负载均衡
 * Datetime:    2020/11/29   下午6:42
 * Author:   王震
 */
public class RandomLoadBalance implements LoadBalance {
    @Override
    public String selectServiceAddress(List<String> serviceAddresses, String rpcServiceName) {
        Random random =new Random();
        // 从当前的列表中随机获取一个可用的地址
        return serviceAddresses.get(random.nextInt(serviceAddresses.size()));
    }
}
