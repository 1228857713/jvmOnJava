package com.wangzhen.jvm.instructions.conversions.i2x;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

public class I2L extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int val1= stack.popInt();
        long val2 = (long) val1;
        stack.pushLong(val2);
    }
}
