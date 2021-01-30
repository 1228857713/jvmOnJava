package com.wangzhen.concurrent.pattern.生产者消费者;



public class App {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(3);
        for(int i =0;i<5;i++){
            // 消费者
            new Thread(()->{

            }).start();
        }
    }
}
