package com.wangzhen.jvm.instructions.base;

import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.ZThread;
import com.wangzhen.jvm.runtimeData.helap.ZClass;
import com.wangzhen.jvm.runtimeData.helap.ZMethod;


/**
 * <clinit>方法由编译器自动收集类中所有静态变量的赋值语句和静态代码块的语句，按照其在源文件中出现的顺序融到<clinit>中。
 * 同时，虚拟机会保证在执行子类的<clinit>方法之前，父类的<clinit>方法已经执行完毕。
 * 所以虚拟机中第一个被执行的<clinit>方法一定是Object类的。
 * 只有当类中存在静态变量赋值语句或者静态代码块时，才会产生<clinit>方法，如果没有这些，那么虚拟机也就没有必要去创建<clinit>方法。
 */
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
