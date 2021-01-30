package com.wangzhen.jvm.instructions.stack.dup;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.Slot;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * Description:
 * Datetime:    2020/9/28   5:04 下午
 * Author:   王震
 */
public class DUP2_X2 extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        Slot slot3 = stack.popSlot();
        Slot slot4 = stack.popSlot();

        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
        stack.pushSlot(slot4);
        stack.pushSlot(slot3);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }
}
