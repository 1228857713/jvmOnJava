package com.wangzhen.algorithm.leetcode.lt_1195;



import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;

    Semaphore fizzSemaphore = new Semaphore(0);
    Semaphore buzzSemaphore = new Semaphore(0);
    Semaphore fizzBuzzSemaphore = new Semaphore(0);
    Semaphore numberSemaphore = new Semaphore(1);
    public FizzBuzz(int n) {
        this.n = n;
    }



    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n ; i++) {
            if(i%3==0&&i%5!=0){
                fizzSemaphore.acquire();
                printFizz.run();
                releaseAll(i+1);
            }
        }

    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n ; i++) {
            if(i%5==0&&i%3!=0){
                buzzSemaphore.acquire();
                printBuzz.run();
                releaseAll(i+1);
            }
        }

    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n ; i++) {
            if(i%3 ==0&&i%5==0){
                fizzBuzzSemaphore.acquire();
                printFizzBuzz.run();
                releaseAll(i+1);
            }
        }

    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n ; i++) {
            if(!(i%3 ==0||i%5==0)){
                numberSemaphore.acquire();
                printNumber.accept(i);
                releaseAll(i+1);

            }
        }
    }

    public void releaseAll(int i){
//        fizzSemaphore.release();
//        buzzSemaphore.release();
//        fizzBuzzSemaphore.release();
//        numberSemaphore.release();
        if(i%3==0&&i%5==0){
            fizzBuzzSemaphore.release();
        }else if(i%3==0){
            fizzSemaphore.release();
        }else if(i%5==0){
            buzzSemaphore.release();
        }else {
            numberSemaphore.release();
        }
    }
}
