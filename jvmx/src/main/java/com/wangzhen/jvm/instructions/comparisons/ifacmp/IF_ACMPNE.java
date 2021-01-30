package com.wangzhen.jvm.instructions.comparisons.ifacmp;

import com.wangzhen.jvm.instructions.base.BranchInstruction;
import com.wangzhen.jvm.instructions.base.BranchLogic;
import com.wangzhen.jvm.runtimeData.ZFrame;

public class IF_ACMPNE extends BranchInstruction {
    @Override
    public void execute(ZFrame frame) {
        if(!IfAcmp._acmp(frame)){
            BranchLogic.branch(frame,offset);
        }
    }
}
