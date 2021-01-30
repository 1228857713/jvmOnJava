package com.wangzhen.jvm.instructions.math.sub;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

public class ISUB extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int num1 = stack.popInt();
        int num2 = stack.popInt();
        int result = num2 - num1;
        stack.pushInt(result);
    }
}
