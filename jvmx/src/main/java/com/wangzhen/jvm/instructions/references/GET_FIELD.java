package com.wangzhen.jvm.instructions.references;

import com.wangzhen.jvm.instructions.base.Index16Instruction;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.Slots;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.*;
/**
 * Author: zhangxin
 * Time: 2017/7/27.
 * Desc: 从实例变量中获取值,并将之放在当前操作数栈
 */
public class GET_FIELD extends Index16Instruction {
    @Override
    public void execute(ZFrame frame)  {
        ZMethod method = frame.getMethod();
        ZClass currentClass = method.getClazz();
        RuntimeConstantPool runtimeConstantPool = currentClass.getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef) runtimeConstantPool.getRuntimeConstant(index).getValue();
        ZField zField = fieldRef.resolvedField();
        if(zField.isStatic()){
            throw new IncompatibleClassChangeError("cannot use static field on instance");
        }
        // 字段描述
        String descriptor=zField.getDescriptor();
        int slotId = zField.getSlotId();
        OperandStack operandStack= frame.getOperandStack();
        ZObject instance = operandStack.popRef();
        if (instance == null) {
            throw new NullPointerException("call " + zField.getName() + " on a null object");
        }
        Slots slots =instance.getFields();
        switch (descriptor.charAt(0)){
            case 'Z'://boolean
            case 'B'://byte
            case 'C'://char
            case 'S'://short
            case 'I'://int
                operandStack.pushInt(slots.getInt(slotId));
                break;
            case 'F':// float
                operandStack.pushFLoat(slots.getFloat(slotId));
                break;
            case 'J':// long
                operandStack.pushLong(slots.getLong(slotId));
                break;
            case 'D'://double
                operandStack.pushDouble(slots.getDouble(slotId));
            case 'L':
            case '[':
                operandStack.pushRef(slots.getRef(slotId));
                break;
            default:
                break;
        }

    }
}
