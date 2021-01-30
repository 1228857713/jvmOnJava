package com.wangzhen.dubbox.common.extension;

/**
 * Description:
 * Datetime:    2020/11/25   9:38
 * Author:   王震
 */
public class Holder<T> {
    private volatile T value;

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}