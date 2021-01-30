package com.wangzhen.jvm.instructions.comparisons.lcmp;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * Description: 用于比较 long 变量,把栈顶的两个 long 变量弹出，进行比较，然后把比较结果（int 型 0、1 或 -1）推入栈顶。
 * Datetime:    2020/9/28   11:29 上午
 * Author:   王震
 */
public class LCMP  extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long v1= operandStack.popLong();
        long v2=operandStack.popLong();
        if(v1>v2){
            operandStack.pushInt(1);
        }else if(v1<v2){
            operandStack.pushInt(2);
        }else {
            operandStack.pushInt(0);
        }
    }
}
