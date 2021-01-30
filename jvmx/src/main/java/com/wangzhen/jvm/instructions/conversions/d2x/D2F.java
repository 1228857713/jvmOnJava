package com.wangzhen.jvm.instructions.conversions.d2x;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

public class D2F extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        double val1= stack.popDouble();
        float val2 = (float) val1;
        stack.pushFLoat(val2);
    }
}
