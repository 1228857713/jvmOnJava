package com.wangzhen.dubbox.registry.zk;

import com.wangzhen.dubbox.common.enums.RpcErrorMessageEnum;
import com.wangzhen.dubbox.common.exception.RpcException;
import com.wangzhen.dubbox.common.extension.ExtensionLoader;
import com.wangzhen.dubbox.loadbalance.LoadBalance;
import com.wangzhen.dubbox.registry.ServiceDiscovery;
import com.wangzhen.dubbox.registry.zk.util.CuratorUtils;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Description:
 * Datetime:    2020/11/27   10:56
 * Author:   王震
 */
@Slf4j
public class ZkServiceDiscovery implements ServiceDiscovery {
    private final LoadBalance loadBalancel;

    public ZkServiceDiscovery() {
        //默认使用随机算法加载负载均衡策略
        this.loadBalancel = ExtensionLoader.getExtensionLoader(LoadBalance.class).getExtension("random");
    }

    @Override
    public InetSocketAddress lookupService(String rpcServiceName) {
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        List<String> serviceUrlList = CuratorUtils.getChildrenNodes(zkClient, rpcServiceName);
        if(serviceUrlList==null&&serviceUrlList.size()==0){
            throw new RpcException(RpcErrorMessageEnum.SERVICE_CAN_NOT_BE_FOUND, rpcServiceName);
        }
        String targetServiceUrl = loadBalancel.selectServiceAddress(serviceUrlList, rpcServiceName);
        log.info("Successfully found the service address:[{}]", targetServiceUrl);
        String[] split = targetServiceUrl.split(":");
        String host = split[0];
        int port = Integer.parseInt(split[1]);
        return new InetSocketAddress(host,port);
    }
}