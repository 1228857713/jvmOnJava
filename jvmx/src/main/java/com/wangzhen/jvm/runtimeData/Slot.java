package com.wangzhen.jvm.runtimeData;

import com.wangzhen.jvm.runtimeData.helap.ZObject;

public class Slot {
    public int num;
    public ZObject ref;

    public Slot() {}

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ZObject getRef() {
        return ref;
    }

    public void setRef(ZObject ref) {
        this.ref = ref;
    }
}
