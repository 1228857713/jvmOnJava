package com.wangzhen.jvm.instructions.math.rem;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

public class LREM extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        long num1 = stack.popLong();
        long num2 = stack.popLong();
        long result = num2/num1;
        stack.pushLong(result);

    }
}
