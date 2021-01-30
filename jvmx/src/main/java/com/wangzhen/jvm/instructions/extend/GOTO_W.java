package com.wangzhen.jvm.instructions.extend;

import com.wangzhen.jvm.instructions.base.ByteCodeReader;
import com.wangzhen.jvm.instructions.base.Instruction;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * Description:
 * Datetime:    2020/9/28   9:08 下午
 * Author:   王震
 */
public class GOTO_W implements Instruction {
    @Override
    public void fetchOperands(ByteCodeReader codeReader) {
        System.out.println("该指令还没有完成");
    }

    @Override
    public void execute(ZFrame frame) {
        System.out.println("该指令还没有完成");
    }
}
