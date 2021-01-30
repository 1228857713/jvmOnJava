package com.wangzhen.jvm.instructions.stores.storexarr;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.instructions.stores.Store;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.ZObject;

/**
 * Description: double[x] = 1.0
 * Datetime:    2020/9/28   4:33 下午
 * Author:   王震
 */
public class DASTORE extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        // 得到要赋的值
        double d = operandStack.popDouble();
        // 得到 数组索引
        int index = operandStack.popInt();
        // 得到数组 对象
        ZObject arrRef = operandStack.popRef();
        Store.checkNotNull(arrRef);
        // 得到数组对象
        double[] refs = arrRef.getDoubles();
        Store.checkIndex(arrRef.getArrayLen(),index);
        refs[index] = d;
    }
}
