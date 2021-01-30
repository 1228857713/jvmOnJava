package com.wangzhen.myspring.demo.web;


import com.wangzhen.myspring.demo.entity.User;
import com.wangzhen.myspring.demo.service.IUserService;
import com.wangzhen.myspring.ioc.annotation.Autowired;
import com.wangzhen.myspring.ioc.annotation.Controller;

/**
 * Description:
 * Datetime:    2020/10/25   4:53 下午
 * Author:   王震
 */
@Controller("/user")
public class UserController {

    @Autowired
    IUserService userService;



    public void queryUser(){
        userService.sayName(new User("wangzhen",18));
    }
}
