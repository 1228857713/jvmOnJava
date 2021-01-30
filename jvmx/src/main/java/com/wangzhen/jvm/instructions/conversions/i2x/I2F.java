package com.wangzhen.jvm.instructions.conversions.i2x;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

public class I2F extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int val1= stack.popInt();
        float val2 = (float) val1;
        stack.pushFLoat(val2);
    }
}
