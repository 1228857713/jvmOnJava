package com.wangzhen.springx.demo;


import com.wangzhen.springx.core.ApplicationContext;
import com.wangzhen.springx.demo.config.Config;
import com.wangzhen.springx.demo.web.UserController;


/**
 * Description:
 * Datetime:    2020/10/25   4:14 下午
 * Author:   王震
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext(Config.class);
        UserController userController = (UserController) applicationContext.getBean(UserController.class);
        userController.queryUser();
    }
}
