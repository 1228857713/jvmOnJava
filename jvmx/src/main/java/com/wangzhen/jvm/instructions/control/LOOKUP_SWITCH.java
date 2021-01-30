package com.wangzhen.jvm.instructions.control;

import com.wangzhen.jvm.instructions.base.ByteCodeReader;
import com.wangzhen.jvm.instructions.base.Instruction;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * Description:
 * Datetime:    2020/9/28   8:39 下午
 * Author:   王震
 */
public class LOOKUP_SWITCH implements Instruction {
    @Override
    public void fetchOperands(ByteCodeReader codeReader) {
        System.out.println("该指令还没有完成");
    }

    @Override
    public void execute(ZFrame frame) {
        System.out.println("该指令还没有完成");
    }
}
