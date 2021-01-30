package com.wangzhen.jvm.instructions.comparisons.fcmp;


import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * Author: zhangxin
 * Time: 2017/5/5 0005.
 * Desc:
 */
public class FCMPL extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        FCMP._fcmp(frame, true);
    }
}
