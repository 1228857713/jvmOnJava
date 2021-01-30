package com.wangzhen.jvm.instructions.math.mul;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

public class LMUL extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        long d1 = stack.popLong();
        long d2 = stack.popLong();
        long result = d1 * d2;
        stack.pushLong(result);
    }
}
