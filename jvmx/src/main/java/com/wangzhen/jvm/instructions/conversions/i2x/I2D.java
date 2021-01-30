package com.wangzhen.jvm.instructions.conversions.i2x;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

public class I2D extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int val1= stack.popInt();
        double val2 = (double) val1;
        stack.pushDouble(val2);
    }
}
