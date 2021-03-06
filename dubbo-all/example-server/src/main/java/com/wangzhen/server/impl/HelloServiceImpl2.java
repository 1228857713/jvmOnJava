package com.wangzhen.server.impl;

import com.wanghzen.api.Hello;
import com.wanghzen.api.HelloService;
import com.wangzhen.dubbox.annotation.RpcService;
import lombok.extern.slf4j.Slf4j;


/**
 * Description:
 * Datetime:    2020/11/29   下午4:13
 * Author:   王震
 */
@Slf4j
@RpcService(group = "test1" , version = "1.0")
public class HelloServiceImpl2 implements HelloService {
    static {
        System.out.println("HelloServiceImpl2 被创建");
    }

    @Override
    public String hello(Hello hello) {
        log.info("HelloServiceImpl2收到: {}.", hello.getMessage());
        String result = "Hello2 description is " + hello.getDescription();
        log.info("HelloServiceImpl2返回: {}.", result);
        return result;
    }
}
