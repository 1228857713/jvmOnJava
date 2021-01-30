package com.wangzhen.jvm.instructions.math.iinc;

import com.wangzhen.jvm.instructions.base.ByteCodeReader;
import com.wangzhen.jvm.instructions.base.Instruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.LocalVars;

public class IINC implements Instruction {
    int index;
    int offset;
    @Override
    public void fetchOperands(ByteCodeReader codeReader) {
        index = codeReader.readUint8();
        offset = codeReader.readInt8();
    }

    @Override
    public void execute(ZFrame frame) {
        LocalVars localVars = frame.getLocalVars();
        int num1= localVars.getInt(index);
        int result = num1 + offset;
        localVars.setInt(index,result);
    }
}
