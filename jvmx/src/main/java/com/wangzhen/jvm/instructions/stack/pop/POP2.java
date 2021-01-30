package com.wangzhen.jvm.instructions.stack.pop;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;

public class POP2 extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {

        frame.getOperandStack().popSlot();
        frame.getOperandStack().popSlot();
    }
}
