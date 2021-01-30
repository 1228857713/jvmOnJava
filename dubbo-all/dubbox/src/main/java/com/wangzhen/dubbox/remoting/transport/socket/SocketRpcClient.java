package com.wangzhen.dubbox.remoting.transport.socket;

import com.wangzhen.dubbox.common.entity.RpcServiceProperties;
import com.wangzhen.dubbox.common.extension.ExtensionLoader;
import com.wangzhen.dubbox.registry.ServiceDiscovery;
import com.wangzhen.dubbox.remoting.dto.RpcRequest;

import com.wangzhen.dubbox.remoting.transport.RpcRequestTransport;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Description:
 * Datetime:    2020/11/24   15:58
 * Author:   王震
 */
@Slf4j
public class SocketRpcClient implements RpcRequestTransport {
    private final ServiceDiscovery serviceDiscovery;

    public SocketRpcClient() {
        this.serviceDiscovery = ExtensionLoader.getExtensionLoader(ServiceDiscovery.class).getExtension("zk");
    }

    @Override
    public Object sendRpcRequest(RpcRequest rpcRequest) {
        String rpcServiceName = RpcServiceProperties.builder().version(rpcRequest.getVersion()).group(rpcRequest.getGroup()).
                serviceName(rpcRequest.getInterfaceName()).build().toRpcServiceName();
        InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(rpcServiceName);
        try (Socket socket = new Socket()){
            socket.setSoTimeout(5000);
            socket.connect(inetSocketAddress);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }
}