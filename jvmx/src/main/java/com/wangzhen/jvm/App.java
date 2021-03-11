package com.wangzhen.jvm;

/**
 * Description:
 * Datetime:    2021/3/9   上午10:22
 * Author:   王震
 */
public class App {
    int i=5;

    private static final  App instance = new App();
    public static App getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        App instance = App.getInstance();
    }
}
