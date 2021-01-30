package com.wangzhen.jvm.instructions.constants;

import com.wangzhen.jvm.classConstant.ConstantClassInfo;
import com.wangzhen.jvm.classConstant.ConstantInfo;
import com.wangzhen.jvm.instructions.base.Index16Instruction;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.RuntimeConstantInfo;
import com.wangzhen.jvm.runtimeData.helap.RuntimeConstantPool;
import com.wangzhen.jvm.runtimeData.helap.ZClass;

/**
 * Description: LDC2_W 和 LDC 的区别是，其获取常量池的常量类型为 Long 和 Double，都是 16bit 宽的
 * Datetime:    2020/9/27   13:52
 * Author:   王震
 */
public class LDC2_W extends Index16Instruction {
    @Override
    public void execute(ZFrame frame)  {
        OperandStack operandStack = frame.getOperandStack();
        ZClass clazz = frame.getMethod().getClazz();
        RuntimeConstantPool runtimeConstantPool = clazz.getRuntimeConstantPool();
        RuntimeConstantInfo runtimeConstant = runtimeConstantPool.getRuntimeConstant(index);
        switch (runtimeConstant.getType()){
            case ConstantClassInfo.CONSTANT_Long_info:{
                operandStack.pushLong((Long) runtimeConstant.getValue());
                break;
            }
            case ConstantInfo.CONSTANT_Double_info:{
                operandStack.pushDouble((Double) runtimeConstant.getValue());
                break;
            }
            default:
                throw new ClassFormatError();


        }
    }
}