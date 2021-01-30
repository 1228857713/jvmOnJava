package com.wangzhen.jvm.instructions.extend;

import com.wangzhen.jvm.instructions.base.BranchInstruction;
import com.wangzhen.jvm.instructions.base.BranchLogic;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.ZObject;

/**
 * Description:
 * Datetime:    2020/9/28   9:09 下午
 * Author:   王震
 */
public class IFNONNULL extends BranchInstruction {
    @Override
    public void execute(ZFrame frame) {
        ZObject ref = frame.getOperandStack().popRef();
        if(ref!=null){
            BranchLogic.branch(frame,offset);
        }

    }
}
