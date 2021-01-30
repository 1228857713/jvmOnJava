package com.wangzhen.jvm.instructions.conversions.i2x;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * Description:
 * Datetime:    2020/9/28   8:06 下午
 * Author:   王震
 */
public class I2B extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int b = operandStack.popInt();
        byte val = (byte) b;
        operandStack.pushInt(val);
    }
}
