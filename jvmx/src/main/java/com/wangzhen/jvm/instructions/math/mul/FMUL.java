package com.wangzhen.jvm.instructions.math.mul;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

public class FMUL extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        float d1 = stack.popFloat();
        float d2 = stack.popFloat();
        float result = d1 * d2;
        stack.pushFLoat(result);
    }
}
