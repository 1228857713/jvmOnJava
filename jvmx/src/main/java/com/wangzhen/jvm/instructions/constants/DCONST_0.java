package com.wangzhen.jvm.instructions.constants;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;

public class DCONST_0 extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        frame.getOperandStack().pushDouble(0.0d);
    }
}
