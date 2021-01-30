package com.wangzhen.jvm.instructions.math.add;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

public class FADD extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        float num1 = stack.popFloat();
        float  num2 = stack.popFloat();
        float  result = num1+num2;
        stack.pushFLoat(result);
    }
}
