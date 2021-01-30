package com.wangzhen.jvm.instructions.comparisons.fcmp;


import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * Author: zhangxin
 * Time: 2017/5/5 0005.
 * Desc: 由于浮点数计算有可能产生NaN（Not a Number）值，所以比较两个浮点数时，除了大于、等于、小于之外， 还有第4种结果：无法比较
 * fcmpg和fcmpl指令的区别就在于对第4种结果的定义;
 * 当两个float变量中至少有一个是NaN时，用fcmpg指令比较的结果是1，而用fcmpl指令比较的结果是-1。
 */
public class FCMP {
    static void _fcmp(ZFrame frame, boolean flag) {
        OperandStack operandStack = frame.getOperandStack();
        float val2 = operandStack.popFloat();
        float val1 = operandStack.popFloat();

        if (val1 < val2) {
            operandStack.pushInt(1);
        } else if (val1 == val2) {
            operandStack.pushInt(0);
        } else if (val1 > val2) {
            operandStack.pushInt(-1);
        } else if (flag) {
            operandStack.pushInt(1);
        } else {
            operandStack.pushInt(-1);
        }

    }
}
