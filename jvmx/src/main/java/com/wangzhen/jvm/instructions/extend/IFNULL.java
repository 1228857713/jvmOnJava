package com.wangzhen.jvm.instructions.extend;

import com.wangzhen.jvm.instructions.base.BranchInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * Description:
 * Datetime:    2020/9/28   9:09 下午
 * Author:   王震
 */
public class IFNULL extends BranchInstruction {
    @Override
    public void execute(ZFrame frame) {
        System.out.println("该指令还没有完成");
    }
}
