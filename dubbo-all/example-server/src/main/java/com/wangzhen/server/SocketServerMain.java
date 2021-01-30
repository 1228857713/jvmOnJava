package com.wangzhen.server;

import com.wanghzen.api.HelloService;
import com.wangzhen.dubbox.common.entity.RpcServiceProperties;
import com.wangzhen.dubbox.remoting.transport.socket.SocketRpcServer;
import com.wangzhen.server.impl.HelloServiceImpl;

/**
 * Description:
 * Datetime:    2020/11/29   下午4:21
 * Author:   王震
 */
public class SocketServerMain {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        SocketRpcServer socketRpcServer = new SocketRpcServer();
        RpcServiceProperties rpcServiceProperties = RpcServiceProperties.builder().group("test1").version("1.0").build();
        socketRpcServer.registerService(helloService,rpcServiceProperties);
        socketRpcServer.start();
    }
}
