package com.wangzhen.jvm.znative;


import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.znative.java.lang.Nclass;
import com.wangzhen.jvm.znative.java.lang.Nobject;
import com.wangzhen.jvm.znative.java.lang.Nthrowable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zachaxy
 * @date 17/12/31
 * desc：native 方法注册中心，所有的 native 方法都要在注册中心进行注册
 */
public class RegisterCenter {
    public static Map<String,NativeMethod> nativeMethods=new ConcurrentHashMap<String,NativeMethod>();

    public static void register (String className,String methodName,String methodDescriptor,NativeMethod nativeMethod){
        String key = className+"~"+methodName+"~"+methodDescriptor;
        nativeMethods.put(key,nativeMethod);
    }
    public static NativeMethod findNativeMethod(String className,String methodName,String methodDescriptor){
        String key = className+"~"+methodName+"~"+methodDescriptor;
        if(nativeMethods.containsKey(key)){
            return nativeMethods.get(key);
        }
        if("()V".equals(methodDescriptor)){
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
        register("java/lang/Class", "getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;", new Nclass.getPrimitiveClass());
        register("java/lang/Class", "getName0", "()Ljava/lang/String;", new Nclass.getName0());
        register("java/lang/Class", "desiredAssertionStatus0", "(Ljava/lang/Class;)Z", new Nclass.desiredAssertionStatus0());
        register("java/lang/Class", "isArray", "()Z", new Nclass.isArray());
/*
    register("java/lang/Class", "isInterface", "()Z", new Nclass.isInterface())
	register("java/lang/Class", "isPrimitive", "()Z", new Nclass.isPrimitive())
	register("java/lang/Class", "getDeclaredFields0", "(Z)[Ljava/lang/reflect/Field;", new Nclass.getDeclaredFields0())
	register("java/lang/Class", "forName0", "(Ljava/lang/String;ZLjava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Class;", new Nclass.forName0())
	register("java/lang/Class", "getDeclaredConstructors0", "(Z)[Ljava/lang/reflect/Constructor;", new Nclass.getDeclaredConstructors0())
	register("java/lang/Class", "getModifiers", "()I", new Nclass.getModifiers())
	register("java/lang/Class", "getSuperclass", "()Ljava/lang/Class;", new Nclass.getSuperclass())
	register("java/lang/Class", "getInterfaces0", "()[Ljava/lang/Class;", new Nclass.getInterfaces0())
	register("java/lang/Class", "getDeclaredMethods0", "(Z)[Ljava/lang/reflect/Method;", new Nclass.getDeclaredMethods0())
	register("java/lang/Class", "getComponentType", "()Ljava/lang/Class;", new Nclass.getComponentType())
	register("java/lang/Class", "isAssignableFrom", "(Ljava/lang/Class;)Z", new Nclass.isAssignableFrom())
 */
        register("java/lang/Throwable", "fillInStackTrace", "(I)Ljava/lang/Throwable;", new Nthrowable.fillInStackTrace());


    }

}
