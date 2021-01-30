package com.wangzhen.jvm.instructions.base;

import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.ZThread;
import com.wangzhen.jvm.runtimeData.helap.ZClass;
import com.wangzhen.jvm.runtimeData.helap.ZMethod;

public class ClassInitLogic {
    public static void initClass(ZClass zClass,ZThread thread){
        zClass.startInit();
        //这里是只是做压栈动作，所以先调用初始化本身在调用初始化父类。等jvm 执行的时候就变成了先调用父类，在调用子类了
        scheduleClinit(zClass,thread);
        initSuperClass(zClass,thread);
    }

    private static void  scheduleClinit(ZClass zClass , ZThread zThread){
         ZMethod clinit = zClass.getMethod("<clinit>","()V");
         if(clinit!=null && clinit.getClazz() == zClass){
            ZFrame frame=  zThread.createFrame(clinit);
            zThread.pushFrame(frame);
         }
    }
    private static void  initSuperClass(ZClass zClass,ZThread zThread){
         if(!zClass.isInterface()){
             ZClass superClass = zClass.getSuperClass();
             if(superClass!=null&& !superClass.isInitStarted()){
                 initClass(superClass,zThread);
             }
         }
    }


}
