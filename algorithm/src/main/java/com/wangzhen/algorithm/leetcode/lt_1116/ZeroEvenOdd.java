package com.wangzhen.algorithm.leetcode.lt_1116;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;

    Semaphore zeroSemaphore = new Semaphore(1);
    Semaphore evenSemaphore = new Semaphore(0);
    Semaphore oddSemaphore = new Semaphore(0);
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zeroSemaphore.acquire();
            printNumber.accept(0);
            release(i+1);
        }

    }
    public void release(int i){
        if(i%2==0){
            evenSemaphore.release();
        }else {
            oddSemaphore.release();
        }
    }

    // 打印偶数
    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i++) {
            // 如果是偶数
            if(i%2 ==0 ){
                evenSemaphore.acquire();
                printNumber.accept(i);
                zeroSemaphore.release();
            }
        }
    }

    // 打印奇数
    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if(i%2!=0){
                oddSemaphore.acquire();
                printNumber.accept(i);
                zeroSemaphore.release();
            }
        }
    }
}


