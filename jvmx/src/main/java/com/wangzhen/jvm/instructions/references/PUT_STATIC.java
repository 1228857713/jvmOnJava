package com.wangzhen.jvm.instructions.references;

import com.wangzhen.jvm.instructions.base.ClassInitLogic;
import com.wangzhen.jvm.instructions.base.Index16Instruction;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.Slots;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.*;


/**
 * Author: zhangxin
 * Time: 2017/7/26.
 * Desc: 为静态变量赋值，所赋的值在操作数栈中
 */
public class PUT_STATIC extends Index16Instruction {
    @Override
    public void execute(ZFrame frame)  {
        ZMethod currentMethod = frame.getMethod();
        ZClass currentClass=currentMethod.getClazz();
        RuntimeConstantPool runtimeConstantPool=currentClass.getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef) runtimeConstantPool.getRuntimeConstant(index).getValue();
        ZField field = fieldRef.resolvedField();

        // 得到该属性的class
        ZClass zClass=field.getClazz();
        // 如果该类还没有 经历过类的初始化，那么初始化该类
        if(!zClass.isInitStarted()){
            frame.revertPc();
            ClassInitLogic.initClass(zClass,frame.getThread());
        }
        // 如果该属性不是静态属性那么需要抛出错误
        if(!field.isStatic()){
            throw new IncompatibleClassChangeError("can't access unstatic field: " + field.getName());
        }
        if (field.isFinal()) {
            if (currentClass != zClass || !"<clinit>".equals(currentMethod.getName())) {
                throw new IllegalAccessError("java.lang.IllegalAccessError");
            }
        }

        // 得到类的描述
        String desc=field.getDescriptor();
        int slotId = field.getSlotId();
        Slots slots = zClass.getStaticVars();
        OperandStack operandStack = frame.getOperandStack();

        switch (desc.charAt(0)){
            case 'Z'://boolean
            case 'B'://byte
            case 'S'://short
            case 'C':// C
            case 'I'://Int
                slots.setInt(slotId,operandStack.popInt());
                break;
            case 'J':// Long
                slots.setLong(slotId,operandStack.popLong());
                break;
            case 'F'://Float
                slots.setFloat(slotId,operandStack.popFloat());
                break;
            case 'D'://Double
                slots.setDouble(slotId,operandStack.popDouble());
                break;
            case 'L':// 对象
            case '[':// 数组
                slots.setRef(slotId,operandStack.popRef());
                break;
            default:
                break;




        }



    }
}
