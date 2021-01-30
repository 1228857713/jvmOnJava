package com.wangzhen.jvm.instructions.math.rem;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

public class DREM extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        double num1 = stack.popFloat();
        double num2 = stack.popFloat();
        double result = num2/num1;
        stack.pushDouble(result);

    }
}
