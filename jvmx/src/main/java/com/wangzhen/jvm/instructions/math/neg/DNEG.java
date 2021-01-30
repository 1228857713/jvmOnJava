package com.wangzhen.jvm.instructions.math.neg;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

/*
negative 否定的
 */
public class DNEG extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        double num = stack.popDouble();
        stack.pushDouble(-num);
    }
}
