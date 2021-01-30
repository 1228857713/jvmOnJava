package com.wangzhen.myspring.demo;


import com.wangzhen.myspring.context.app.ApplicationContext;
import com.wangzhen.myspring.context.app.impl.AnnotationConfigApplicationContext;
import com.wangzhen.myspring.demo.config.Config;
import com.wangzhen.myspring.demo.web.UserController;

/**
 * Description:
 * Datetime:    2020/10/25   4:14 下午
 * Author:   王震
 */
public class App {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        UserController userController = (UserController) applicationContext.getBean("userController");
        userController.queryUser();
    }
}
