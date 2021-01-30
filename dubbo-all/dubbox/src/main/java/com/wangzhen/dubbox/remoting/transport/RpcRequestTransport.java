package com.wangzhen.dubbox.remoting.transport;

import com.wangzhen.dubbox.remoting.dto.RpcRequest;

/**
 * Description:
 * Datetime:    2020/11/23   15:42
 * Author:   王震
 */
public interface RpcRequestTransport {
    Object sendRpcRequest(RpcRequest rpcRequest);
}
