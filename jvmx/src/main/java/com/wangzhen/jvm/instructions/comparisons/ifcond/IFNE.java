package com.wangzhen.jvm.instructions.comparisons.ifcond;

import com.wangzhen.jvm.instructions.base.BranchInstruction;
import com.wangzhen.jvm.instructions.base.BranchLogic;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * Description:
 * Datetime:    2020/9/28   13:19
 * Author:   王震
 */
public class IFNE extends BranchInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int val = operandStack.popInt();
        if(val!=0){
            BranchLogic.branch(frame,offset);
        }
    }
}