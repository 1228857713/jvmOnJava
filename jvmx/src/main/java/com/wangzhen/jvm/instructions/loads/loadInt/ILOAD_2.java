package com.wangzhen.jvm.instructions.loads.loadInt;

import com.wangzhen.jvm.instructions.base.Index8Instruction;
import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.instructions.loads.Load;
import com.wangzhen.jvm.runtimeData.ZFrame;

public class ILOAD_2 extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        Load.iLoad(frame,2);
    }
}
