package com.wangzhen.jvm.runtimeData.helap;

public class RuntimeConstantInfo<T> {
    private T value;
    private int type;

    public RuntimeConstantInfo(T value, int type) {
        this.value = value;
        this.type = type;
    }


    public T getValue() {
        return value;
    }

    public int getType() {
        return type;
    }
}
