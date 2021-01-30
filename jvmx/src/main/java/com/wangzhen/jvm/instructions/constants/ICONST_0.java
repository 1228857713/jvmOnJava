package com.wangzhen.jvm.instructions.constants;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * ICONST_0 ->ICONST_5  表示向操作数栈中 推入一个 int 型数据
 */
public class ICONST_0 extends NoOperandsInstruction {

    @Override
    public void execute(ZFrame frame) {
        frame.getOperandStack().pushInt(0);
    }
}
