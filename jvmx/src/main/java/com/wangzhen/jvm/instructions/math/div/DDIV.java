package com.wangzhen.jvm.instructions.math.div;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

public class DDIV extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        double num1 = stack.popDouble();
        double num2 = stack.popDouble();
        double result = num2/num1;
        stack.pushDouble(result);
    }
}
