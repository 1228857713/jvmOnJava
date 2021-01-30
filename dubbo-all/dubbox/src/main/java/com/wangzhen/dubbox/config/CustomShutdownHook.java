package com.wangzhen.dubbox.config;

import com.wangzhen.dubbox.common.util.concurrent.threadpool.ThreadPoolFactoryUtils;
import com.wangzhen.dubbox.registry.zk.util.CuratorUtils;
import com.wangzhen.dubbox.remoting.transport.netty.server.NettyRpcServer;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadFactory;

/**
 * Description:
 * Datetime:    2020/11/26   15:48
 * Author:   王震
 */
@Slf4j
public class CustomShutdownHook  {
    private static final CustomShutdownHook CUSTOM_SHUTDOWN_HOOK = new CustomShutdownHook();
    public static CustomShutdownHook getCustomShutdownHook() {

        return CUSTOM_SHUTDOWN_HOOK;
    }

    public void clearAll(){
        log.info("addShutdownHook for clearAll");
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try {
                InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), NettyRpcServer.PORT);
                // 清除掉zk 的所有的注册信息
                CuratorUtils.clearRegistry(CuratorUtils.getZkClient(),inetSocketAddress);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            // 停止掉所有的线程池
            ThreadPoolFactoryUtils.shutDownAllThreadPool();

        }));
    }
}