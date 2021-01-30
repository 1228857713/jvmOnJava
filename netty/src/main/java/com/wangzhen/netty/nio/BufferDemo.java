package com.wangzhen.netty.nio;

import java.nio.IntBuffer;

/**
 * Description:
 * Datetime:    2020/9/21   7:54 下午
 * Author:   王震
 */
public class BufferDemo {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i*2);
        }
        System.out.println(intBuffer.toString());
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
