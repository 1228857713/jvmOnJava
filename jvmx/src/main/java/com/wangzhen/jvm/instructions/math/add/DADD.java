package com.wangzhen.jvm.instructions.math.add;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

public class DADD extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        double d1 = stack.popDouble();
        double d2 = stack.popDouble();
        double result = d1+d2;
        stack.pushDouble(result);
    }
}
