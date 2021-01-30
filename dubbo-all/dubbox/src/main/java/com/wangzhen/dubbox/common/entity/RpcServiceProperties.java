package com.wangzhen.dubbox.common.entity;

import lombok.*;

/**
 * Description:
 * Datetime:    2020/11/23   15:52
 * Author:   王震
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RpcServiceProperties {
    private String version;
    private String group;
    private String serviceName;
    public String toRpcServiceName(){
        return this.serviceName+this.group+this.version;
    }
}