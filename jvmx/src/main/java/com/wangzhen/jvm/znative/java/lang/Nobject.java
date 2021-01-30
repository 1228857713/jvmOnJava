package com.wangzhen.jvm.znative.java.lang;

import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.ZObject;
import com.wangzhen.jvm.znative.NativeMethod;

public class Nobject {
    public static class getClass implements NativeMethod{

        @Override
        public void run(ZFrame frame) {

            ZObject self = frame.getLocalVars().getRef(0);
            ZObject Jobject=self.getClazz().getjObject();
            frame.getOperandStack().pushRef(Jobject);
        }
    }
}
