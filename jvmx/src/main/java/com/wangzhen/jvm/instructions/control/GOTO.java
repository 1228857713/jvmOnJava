package com.wangzhen.jvm.instructions.control;

import com.wangzhen.jvm.instructions.base.BranchInstruction;
import com.wangzhen.jvm.instructions.base.BranchLogic;
import com.wangzhen.jvm.runtimeData.ZFrame;

public class GOTO extends BranchInstruction {
    @Override
    public void execute(ZFrame frame)  {
        BranchLogic.branch(frame,offset);
    }
}
