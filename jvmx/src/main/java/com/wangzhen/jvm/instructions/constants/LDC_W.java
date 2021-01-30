package com.wangzhen.jvm.instructions.constants;

import com.wangzhen.jvm.instructions.base.ByteCodeReader;

/**
 * Description:  LDC_W和 LDC 的 execute 是完全一样的，唯一的区别就是取操作数的位宽，w 取16位，非 w 取8位
 * 所以 LDC_W 复用了 LDC 的 execute 过程，但是重写了其 fetch 方法，改为取16位宽
 * Datetime:    2020/9/27   11:19
 * Author:   王震
 */
public class LDC_W extends LDC{
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        index = reader.readInt16();
    }

}