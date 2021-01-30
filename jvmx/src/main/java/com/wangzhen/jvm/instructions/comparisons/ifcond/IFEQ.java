package com.wangzhen.jvm.instructions.comparisons.ifcond;

import com.wangzhen.jvm.instructions.base.BranchInstruction;
import com.wangzhen.jvm.instructions.base.BranchLogic;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * Description: if<cond>指令把操作数栈顶的 int 变量弹出，然后跟 0 进行比较，满足条件则跳转
 * Datetime:    2020/9/28   10:41
 * Author:   王震
 */
public class IFEQ extends BranchInstruction {
    @Override
    public void execute(ZFrame frame)  {
        OperandStack operandStack = frame.getOperandStack();
        int val = operandStack.popInt();
        if(val==0){
            BranchLogic.branch(frame,offset);
        }

    }
}