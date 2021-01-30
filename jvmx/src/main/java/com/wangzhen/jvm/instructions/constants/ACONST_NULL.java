package com.wangzhen.jvm.instructions.constants;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * 将 null push 到操作数栈顶
 */
public class ACONST_NULL extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        frame.getOperandStack().pushRef(null);
    }
}
