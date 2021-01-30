package com.wangzhen.client;

import com.wanghzen.api.Hello;
import com.wanghzen.api.HelloService;
import com.wangzhen.dubbox.common.entity.RpcServiceProperties;
import com.wangzhen.dubbox.proxy.RpcClientProxy;
import com.wangzhen.dubbox.remoting.transport.RpcRequestTransport;
import com.wangzhen.dubbox.remoting.transport.socket.SocketRpcClient;

/**
 * Description:
 * Datetime:    2020/11/29   下午5:39
 * Author:   王震
 */
public class SocketClientMain {
    public static void main(String[] args) {
        RpcRequestTransport rpcRequestTransport = new SocketRpcClient();
        RpcServiceProperties rpcServiceProperties = RpcServiceProperties.builder()
                .group("test1").version("1.0").build();
        RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcRequestTransport, rpcServiceProperties);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        for (int i = 0; i < 100; i++) {

            try {
                Thread.sleep(1000);
                String result = helloService.hello(new Hello("wangzhen", "handsome"));
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
