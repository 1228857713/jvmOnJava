package com.wangzhen.jvm.instructions.loads.loadxarr;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.instructions.loads.Load;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.ZObject;

/**
 * Description:  eq short s = shorts[2]
 * Datetime:    2020/9/28   14:12
 * Author:   王震
 */
public class SALOAD extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        // 数组的索引值
        int index = operandStack.popInt();
        // 数组对象的引用
        ZObject arrRef = operandStack.popRef();
        Load.checkNotNull(arrRef);
        // 得到真正的short数组
        short[] shorts = arrRef.getShorts();
        Load.checkIndex(shorts.length,index);
        operandStack.pushInt(shorts[index]);
    }
}