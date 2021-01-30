package com.wangzhen.algorithm.leetcode.lt_1114;

import java.util.concurrent.Semaphore;

class Foo {

    Semaphore two = new Semaphore(0,false);
    Semaphore three = new Semaphore(0,false);
    volatile short flag;
    public Foo() {

    }
    public void first(Runnable printFirst) throws InterruptedException {


            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            two.release();

    }

    public void second(Runnable printSecond) throws InterruptedException {
        two.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        three.release();
        two.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        three.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        three.release();
    }
}
