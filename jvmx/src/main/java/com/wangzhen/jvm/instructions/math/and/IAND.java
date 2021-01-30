package com.wangzhen.jvm.instructions.math.and;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;

public class IAND extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int num1 = stack.popInt();
        int num2 = stack.popInt();
        int result = num1 & num2 ;
        stack.pushInt(result);
    }
}
