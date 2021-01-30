package com.wangzhen.jvm.instructions.constants;

import com.wangzhen.jvm.instructions.base.ByteCodeReader;
import com.wangzhen.jvm.instructions.base.Instruction;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 *  SIPUSH 指令从操作数（code）中获取一个 short型整数 让后扩展成int 类型 推入到操作数栈中
 */
public class SIPUSH implements Instruction {
    int val;
    @Override
    public void fetchOperands(ByteCodeReader codeReader) {
        val = codeReader.readInt16();
    }

    @Override
    public void execute(ZFrame frame) {
        frame.getOperandStack().pushInt((val+65536)%65536);
    }
}
