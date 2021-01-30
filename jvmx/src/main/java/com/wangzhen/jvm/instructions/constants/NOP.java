package com.wangzhen.jvm.instructions.constants;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;


/**
 * nop指令是最简单的一条指令，因为它什么也不做
 */
public class NOP extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame zFrame) {

    }
}
