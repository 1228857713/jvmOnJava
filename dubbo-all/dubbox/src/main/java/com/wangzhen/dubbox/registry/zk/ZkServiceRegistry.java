package com.wangzhen.dubbox.registry.zk;

import com.wangzhen.dubbox.registry.ServiceRegistry;
import com.wangzhen.dubbox.registry.zk.util.CuratorUtils;
import org.apache.curator.framework.CuratorFramework;


import java.net.InetSocketAddress;

/**
 * Description:
 * Datetime:    2020/11/27   10:56
 * Author:   王震
 */
public class ZkServiceRegistry  implements  ServiceRegistry {
    @Override
    public void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress) {
        String servicePath = CuratorUtils.ZK_REGISTER_ROOT_PATH + "/" + rpcServiceName + inetSocketAddress.toString();
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        CuratorUtils.createPersistentNode(zkClient,servicePath);

    }
}