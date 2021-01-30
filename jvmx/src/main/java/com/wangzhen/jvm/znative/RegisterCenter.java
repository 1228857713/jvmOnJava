package com.wangzhen.jvm.znative;


import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.znative.java.lang.Nobject;

import java.util.HashMap;

/**
 * @author zachaxy
 * @date 17/12/31
 * desc：native 方法注册中心，所有的 native 方法都要在注册中心进行注册
 */
public class RegisterCenter {
    public static HashMap<String,NativeMethod> nativeMethods=new HashMap<>();

    public static void register (String className,String methodName,String methodDescriptor,NativeMethod nativeMethod){
        String key = className+"~"+methodName+"~"+methodDescriptor;
        nativeMethods.put(key,nativeMethod);
    }
    public static NativeMethod findNativeMethod(String className,String methodName,String methodDescriptor){
        String key = className+"~"+methodName+"~"+methodDescriptor;
        if(nativeMethods.containsKey(key)){
            return nativeMethods.get(key);
        }
        if("".equals(methodDescriptor)){
            if ("registerNatives".equals(methodName) || "initIDs".equals(methodName)) {
                return new NativeMethod() {
                    @Override
                    public void run(ZFrame frame) {

                    }
                };
            }
        }
        return null;
    }

    public static void init(){
        register("java/lang/Object", "getClass", "()Ljava/lang/Class;", new Nobject.getClass());
//        register("java/lang/Class", "getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;", new Nclass.getPrimitiveClass());
//        register("java/lang/Class", "getName0", "()Ljava/lang/String;", new Nclass.getName0());
//        register("java/lang/Class", "desiredAssertionStatus0", "(Ljava/lang/Class;)Z", new Nclass.desiredAssertionStatus0());
//        register("java/lang/Class", "isArray", "()Z", new Nclass.isArray());
//        register("java/lang/Throwable", "fillInStackTrace", "(I)Ljava/lang/Throwable;", new Nthrowable.fillInStackTrace());


    }

}
