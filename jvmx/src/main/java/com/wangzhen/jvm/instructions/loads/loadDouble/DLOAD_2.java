package com.wangzhen.jvm.instructions.loads.loadDouble;

import com.wangzhen.jvm.instructions.base.Index8Instruction;
import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.instructions.loads.Load;
import com.wangzhen.jvm.runtimeData.ZFrame;

public class DLOAD_2 extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        Load.dLoad(frame,2);
    }
}
