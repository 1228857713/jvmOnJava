package com.wangzhen.jvm.instructions.base;

import com.wangzhen.jvm.runtimeData.ZFrame;

public interface Instruction {
    //从字节码中提取操作数
    void fetchOperands(ByteCodeReader codeReader);

    // 执行指令逻辑
    void execute(ZFrame frame) ;
}
