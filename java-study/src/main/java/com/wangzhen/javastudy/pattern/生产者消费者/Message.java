package com.wangzhen.javastudy.pattern.生产者消费者;

public class Message {
    private String id;
    private Object msg;

    public Message(String id, Object msg) {
        this.id = id;
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
