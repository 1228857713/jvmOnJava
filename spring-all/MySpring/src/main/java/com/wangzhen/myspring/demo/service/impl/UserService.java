package com.wangzhen.myspring.demo.service.impl;


import com.wangzhen.myspring.demo.entity.User;
import com.wangzhen.myspring.demo.service.IUserService;
import com.wangzhen.myspring.ioc.annotation.Component;

/**
 * Description:
 * Datetime:    2020/10/25   4:56 下午
 * Author:   王震
 */
@Component("userService")
public class UserService implements IUserService {

    @Override
    public void sayName(User usre) {
        System.out.println(usre.toString());
    }
}
