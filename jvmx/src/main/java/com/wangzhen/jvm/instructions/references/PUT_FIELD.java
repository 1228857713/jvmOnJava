package com.wangzhen.jvm.instructions.references;

import com.wangzhen.jvm.instructions.base.Index16Instruction;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.*;

/**
 * Description:
 * Datetime:    2020/9/28   8:51 下午
 * Author:   王震
 */
public class PUT_FIELD extends Index16Instruction {
    @Override
    public void execute(ZFrame frame) {
        ZMethod currentMethod = frame.getMethod();
        ZClass currentClass = currentMethod.getClazz();
        RuntimeConstantPool runtimeConstantPool = currentClass.getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef) runtimeConstantPool.getRuntimeConstant(index).getValue();
        ZField field = fieldRef.resolvedField();
        ZClass clazz = field.getClazz();
        ZObject instance;
        if (field.isFinal()) {
            if (currentClass != clazz || "<clinit>".equals(currentMethod.getName())) {
                throw new IllegalAccessError(field.getName()+" can't be assigned out of instance");
            }
        }

        // 得到该属性在对象中的 slot 的位置
        int slotId = field.getSlotId();
        String descriptor = field.getDescriptor();
        OperandStack stack = frame.getOperandStack();
        switch (descriptor.charAt(0)){
            case  'Z':
            case  'B':
            case  'C':
            case  'S':
            case  'I': {
                int value = stack.popInt();
                instance = stack.popRef();
                if (instance == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                instance.getFields().setInt(slotId,value);
                break;
            }
            case 'F':{
                float val = stack.popFloat();
                instance = stack.popRef();
                if (instance == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                instance.getFields().setFloat(slotId, val);
                break;
            }
            case 'J':{
                long val = stack.popLong();
                instance = stack.popRef();
                if (instance == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                instance.getFields().setLong(slotId, val);
                break;
            }
            case 'D':{
                double val = stack.popDouble();
                instance = stack.popRef();
                if (instance == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                instance.getFields().setDouble(slotId, val);
                break;
            }
            case 'L':
            case '[':{
                ZObject value = stack.popRef();
                instance = stack.popRef();
                if (instance == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                instance.getFields().setRef(slotId,value);

            }
            default:
                break;




        }


    }
}
