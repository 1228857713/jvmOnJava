package com.wangzhen.jvm.instructions.stores.storexarr;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.instructions.stores.Store;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.ZObject;

/**
 * Description: 对数组的某一个元素进行赋值 x[0] = y
 * Datetime:    2020/9/24   17:02
 * Author:   王震
 */
public class IASTORE extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame)  {
        OperandStack operandStack = frame.getOperandStack();
        // 得到要赋的值
        int val = operandStack.popInt();
        // 得到 数组索引
        int index = operandStack.popInt();
        // 得到数组 对象
        ZObject arrRef = operandStack.popRef();
        Store.checkNotNull(arrRef);
        // 得到数组对象
        int[] ints = arrRef.getInts();
        Store.checkIndex(arrRef.getArrayLen(),index);
        ints[index] = val;

    }
}
