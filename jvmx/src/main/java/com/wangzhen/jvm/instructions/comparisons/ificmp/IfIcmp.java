package com.wangzhen.jvm.instructions.comparisons.ificmp;

import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * Description:  if_icmp<cond>指令把栈顶的两个 int 变量弹出，然后进行比较，满足条件则跳转。跳转条件和 if指令类似。
 * Datetime:    2020/9/28   11:00
 * Author:   王震
 */
public class IfIcmp {
    static int[] _icmpPop(ZFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int []res = new int[2];
        res[0]=operandStack.popInt();
        res[1]=operandStack.popInt();
        return res;
    }
}