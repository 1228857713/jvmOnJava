package com.wangzhen.jvm.instructions.control;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.ZThread;

/**
 * Description:
 * Datetime:    2020/9/28   8:39 下午
 * Author:   王震
 */
public class LRETURN extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        ZThread thread = frame.getThread();
        ZFrame currentFrame = thread.popFrame();
        ZFrame invokeFrame = thread.getCurrentFrame();
        invokeFrame.getOperandStack().pushLong(currentFrame.getOperandStack().popLong());
    }
}
