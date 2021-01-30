package com.wangzhen.jvm.instructions.constants;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;


/**
 * Author: zhangxin
 * Time: 2017/5/5 0005.
 * Desc: Push float
 */
public class FCONST_1 extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        frame.getOperandStack().pushFLoat(1.0f);
    }
}
