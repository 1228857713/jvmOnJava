package com.wangzhen.jvm.instructions.conversions.f2x;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

public class F2L extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        float val1= stack.popLong();
        long val2 = (long) val1;
        stack.pushLong(val2);
    }
}
