package com.wangzhen.jvm.instructions.conversions.d2x;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

public class D2L extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        double val1= stack.popDouble();
        long val2 = (long) val1;
        stack.pushFLoat(val2);
    }
}
