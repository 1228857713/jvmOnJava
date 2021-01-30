package com.wangzhen.concurrent;

import javax.sound.midi.Soundbank;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * Datetime:    2020/9/29   16:48
 * Author:   王震
 */
public class App {
    public static void main(String[] args) {

        System.out.println(Integer.MAX_VALUE);
        int i = (2<<31);
        System.out.println(i);
        System.out.println(0x7fffffff);
        System.out.println((1 << 29) - 1 ); // 0xfffffff



    }
}