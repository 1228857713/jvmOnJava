package com.wangzhen.jvm.instructions.stack.dup;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.Slot;

public class DUP extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot = stack.popSlot();
        stack.pushSlot(slot);
        stack.pushSlot(slot);
    }
}
