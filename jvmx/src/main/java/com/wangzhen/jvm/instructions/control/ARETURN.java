package com.wangzhen.jvm.instructions.control;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.ZThread;

/**
 * Description: 返回值为 实例对象 的 return 指令
 * Datetime:    2020/9/28   8:41 下午
 * Author:   王震
 */
public class ARETURN extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        ZThread thread = frame.getThread();
        ZFrame currentFrame = thread.popFrame();
        ZFrame invokeFrame = thread.getCurrentFrame();
        invokeFrame.getOperandStack().pushRef(currentFrame.getOperandStack().popRef());


    }
}
