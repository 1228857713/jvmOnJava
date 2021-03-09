package com.wangzhen.jvm.instructions.references;

import com.wangzhen.jvm.instructions.base.ClassInitLogic;
import com.wangzhen.jvm.instructions.base.Index16Instruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.ClassRef;
import com.wangzhen.jvm.runtimeData.helap.RuntimeConstantPool;
import com.wangzhen.jvm.runtimeData.helap.ZClass;
import com.wangzhen.jvm.runtimeData.helap.ZObject;

/**
 *  NEW 指令这里其实主要就是创建一个对象为对象的实例变量分配空间
 *  后续指令会调用类的构造方法为实例变量赋予对应的值
 */
public class NEW extends Index16Instruction {

    @Override
    public void execute(ZFrame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        ClassRef classRef = (ClassRef) runtimeConstantPool.getRuntimeConstant(index).getValue();
        ZClass zClass = classRef.resolvedClass();
        // 看这个类是否已经初始化过，这个是类的初始化不是实例的初始化
        if(!zClass.isInitStarted()){
            //执行类的初始化需要 重新执行之前的new 方法
            frame.revertPc();
            ClassInitLogic.initClass(zClass,frame.getThread());
            return;
        }
        // 如果这个 class 是接口或者是抽象方法直接报错
        if(zClass.isInterface()||zClass.isAbstract()){
            throw new InstantiationError(zClass.thisClassName+"是接口或者抽象方法，不能被new");
        }

        ZObject object = zClass.newObject();
        frame.getOperandStack().pushRef(object);

    }
}
