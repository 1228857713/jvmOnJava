package com.wangzhen.dubbox.remoting.dto;


import com.wangzhen.dubbox.common.entity.RpcServiceProperties;
import lombok.*;

import java.io.Serializable;

/**
 * @author shuang.kou
 * @createTime 2020年05月10日 08:24:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;
    private String requestId;
    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
    private String version;
    private String group;

    public RpcServiceProperties toRpcProperties() {
        return RpcServiceProperties.builder().serviceName(this.getInterfaceName())
                .version(this.getVersion())
                .group(this.getGroup()).build();
    }
}
