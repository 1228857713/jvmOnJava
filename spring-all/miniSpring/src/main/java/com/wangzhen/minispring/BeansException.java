package com.wangzhen.minispring;

/**
 * Description:
 * Datetime:    2020/12/8   下午3:43
 * Author:   王震
 */
public class BeansException extends RuntimeException{
    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
