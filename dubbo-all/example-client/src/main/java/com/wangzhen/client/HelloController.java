package com.wangzhen.client;

import com.wanghzen.api.Hello;
import com.wanghzen.api.HelloService;
import com.wangzhen.dubbox.annotation.RpcReference;
import javafx.scene.control.cell.CheckBoxListCell;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Datetime:    2020/11/29   下午5:34
 * Author:   王震
 */

@Component
public class HelloController {

    @RpcReference(group = "test1",version = "1.0")
    private HelloService helloService;
    public  void test(String[] args) {
        Hello hello = new Hello("hello","hello world");
        String result = helloService.hello(hello);
    }
}
