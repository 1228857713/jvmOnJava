package com.wangzhen.dubbox.remoting.transport.socket;

import com.wangzhen.dubbox.common.factory.SingletonFactory;
import com.wangzhen.dubbox.remoting.dto.RpcRequest;
import com.wangzhen.dubbox.remoting.dto.RpcResponse;
import com.wangzhen.dubbox.remoting.handler.RpcRequestHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Description: 服务端处理请求的 线程
 * Datetime:    2020/11/27   10:32
 * Author:   王震
 */
@Slf4j
public class SocketRpcRequestHandlerRunnable implements  Runnable {

    private final Socket socket;
    private final RpcRequestHandler rpcRequestHandler;

    public SocketRpcRequestHandlerRunnable(Socket socket) {
        this.socket = socket;
        this.rpcRequestHandler = SingletonFactory.getInstance(RpcRequestHandler.class);
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            Object handle = rpcRequestHandler.handle(rpcRequest);
            objectOutputStream.writeObject(RpcResponse.success(handle,rpcRequest.getRequestId()));
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("server handle message from client by thread: [{}]", Thread.currentThread().getName());

    }
}