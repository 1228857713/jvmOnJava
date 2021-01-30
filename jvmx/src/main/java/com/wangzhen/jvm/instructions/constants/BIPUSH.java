package com.wangzhen.jvm.instructions.constants;

import com.wangzhen.jvm.instructions.base.ByteCodeReader;
import com.wangzhen.jvm.instructions.base.Instruction;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 *  从操作数（code）中读取一个 byte 然后在转换为int 推入到操作数
 */
public class BIPUSH implements Instruction {
    int val;
    @Override
    public void fetchOperands(ByteCodeReader codeReader) {
        val = codeReader.readInt8();
    }

    @Override
    public void execute(ZFrame frame) {
        frame.getOperandStack().pushInt((val + 256) % 256);
    }


}
