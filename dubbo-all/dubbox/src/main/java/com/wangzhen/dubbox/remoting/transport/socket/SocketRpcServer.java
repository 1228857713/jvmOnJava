package com.wangzhen.dubbox.remoting.transport.socket;


import com.wangzhen.dubbox.common.entity.RpcServiceProperties;
import com.wangzhen.dubbox.common.factory.SingletonFactory;
import com.wangzhen.dubbox.common.util.concurrent.threadpool.ThreadPoolFactoryUtils;
import com.wangzhen.dubbox.provider.ServiceProvider;
import com.wangzhen.dubbox.provider.ServiceProviderImpl;
import com.wangzhen.dubbox.remoting.transport.netty.server.NettyRpcServer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;

/**
 * Description:
 * Datetime:    2020/11/27   14:44
 * Author:   王震
 */
@Slf4j
public class SocketRpcServer {

    private final ExecutorService threadPool;
    private final ServiceProvider serviceProvider;


    public SocketRpcServer() {
        this.threadPool = ThreadPoolFactoryUtils.createCustomThreadPoolIfAbsent("socket-server-rpc-pool");
        this.serviceProvider = SingletonFactory.getInstance(ServiceProviderImpl.class);
    }


    public void registerService(Object service) {
        serviceProvider.publishService(service);
    }


    public void registerService(Object service, RpcServiceProperties rpcServiceProperties) {
        serviceProvider.publishService(service, rpcServiceProperties);
    }

    public void start(){
        try {
            ServerSocket serverSocket = new ServerSocket();
            String host = InetAddress.getLocalHost().getHostAddress();
            serverSocket.bind(new InetSocketAddress(host, NettyRpcServer.PORT));
            Socket socket;
            while ((socket = serverSocket.accept())!=null){
                log.info("client connected [{}]", socket.getInetAddress());
                threadPool.execute(new SocketRpcRequestHandlerRunnable(socket));
            }
        } catch (IOException e) {
            log.info(e.toString());
        }
    }
}