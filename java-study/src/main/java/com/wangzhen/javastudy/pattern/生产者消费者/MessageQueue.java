package com.wangzhen.javastudy.pattern.生产者消费者;

import java.util.LinkedList;

public class MessageQueue {
    private LinkedList<Message> messages;
    private int capacity;

    public MessageQueue( int capacity) {
        messages = new LinkedList<Message>();
        this.capacity = capacity;
    }

    public void putMessage(Message message){
        //synchronized (messages)
    }

    public Message getMessage(){

        return null;
    }
}
