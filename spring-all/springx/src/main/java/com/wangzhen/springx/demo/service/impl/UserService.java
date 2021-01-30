package com.wangzhen.springx.demo.service.impl;

import com.wangzhen.springx.demo.entity.User;
import com.wangzhen.springx.demo.service.IUserService;
import com.wangzhen.springx.ioc.annotation.Component;

/**
 * Description:
 * Datetime:    2020/10/25   4:56 下午
 * Author:   王震
 */
@Component
public class UserService implements IUserService {

    @Override
    public void sayName(User usre) {
        System.out.println(usre.toString());
    }
}
