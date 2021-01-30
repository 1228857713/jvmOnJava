package com.wangzhen.jvm.znative;

import com.wangzhen.jvm.runtimeData.ZFrame;

public interface NativeMethod {
    public void run(ZFrame frame);
}
