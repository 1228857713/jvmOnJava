package com.wangzhen.jvm.instructions.comparisons.ificmp;

import com.wangzhen.jvm.instructions.base.BranchInstruction;
import com.wangzhen.jvm.instructions.base.BranchLogic;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * Description: 等于判断
 * Datetime:    2020/9/28   11:04
 * Author:   王震
 */
public class IF_ICMPEQ extends BranchInstruction {
    @Override
    public void execute(ZFrame frame) {
        int[] res = IfIcmp._icmpPop(frame);
        if(res[0]==res[1]){
            BranchLogic.branch(frame,offset);
        }

    }
}