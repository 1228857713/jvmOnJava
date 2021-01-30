package com.wangzhen.jvm.instructions.stores.storedouble;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.instructions.stores.Store;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * Author: zhangxin
 * Time: 2017/5/5 0005.
 * Desc:
 */
public class DSTORE_0 extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        Store.dstote(frame,0);
    }
}
