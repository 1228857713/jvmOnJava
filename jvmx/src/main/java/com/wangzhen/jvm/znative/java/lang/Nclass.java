package com.wangzhen.jvm.znative.java.lang;

import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.StringPool;
import com.wangzhen.jvm.runtimeData.helap.ZObject;
import com.wangzhen.jvm.znative.NativeMethod;

public class Nclass {
    public static class getPrimitiveClass implements NativeMethod{

        @Override
        public void run(ZFrame frame) {
            ZObject zObject = frame.getLocalVars().getRef(0);
           // String name= StringPool
        }
    }
}
