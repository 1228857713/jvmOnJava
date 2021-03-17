package com.wangzhen.jvm.znative.java.lang;

import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.StringPool;
import com.wangzhen.jvm.runtimeData.helap.ZClass;
import com.wangzhen.jvm.runtimeData.helap.ZClassLoader;
import com.wangzhen.jvm.runtimeData.helap.ZObject;
import com.wangzhen.jvm.znative.NativeMethod;

public class Nclass {
    // static native Class<?> getPrimitiveClass(String name);
    // (Ljava/lang/String;)Ljava/lang/Class;
    // 该方法是获取基本类型的类对象;
    public static class getPrimitiveClass implements NativeMethod {
        @Override
        public void run(ZFrame frame) {
            ZObject nameObj = frame.getLocalVars().getRef(0);
            String name = StringPool.realString(nameObj);
            ZClassLoader classLoader = frame.getMethod().getClazz().getLoader();
            ZObject jObject = classLoader.loadClass(name).getjObject();
            frame.getOperandStack().pushRef(jObject);
        }
    }

    public static class getName0 implements NativeMethod {
        @Override
        public void run(ZFrame frame) {
            ZObject self = frame.getLocalVars().getRef(0);
            ZClass clazz = (ZClass) self.extra;
            String name = clazz.getJavaName();
            ZObject nameObj = StringPool.jString(clazz.getLoader(), name);
            frame.getOperandStack().pushRef(nameObj);
        }
    }

    public static class desiredAssertionStatus0 implements NativeMethod {
        @Override
        public void run(ZFrame frame) {
            frame.getOperandStack().pushBoolean(false);
        }
    }

    public static class isArray implements NativeMethod {
        @Override
        public void run(ZFrame frame) {
            ZObject self = frame.getLocalVars().getRef(0);
            ZClass clazz = (ZClass) self.extra;
            frame.getOperandStack().pushBoolean(clazz.isArray());
        }
    }
}
