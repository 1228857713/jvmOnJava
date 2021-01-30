package com.wangzhen.jvm.instructions.comparisons.dcmp;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;

public class DCMPL extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame)  {
        DCMP._dcmp(frame,false);
    }
}
