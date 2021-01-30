package com.wangzhen.algorithm.leetcode.lt_1195;



import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz2 {
    private int n;

    Semaphore fizzSemaphore = new Semaphore(0);
    Semaphore buzzSemaphore = new Semaphore(0);
    Semaphore fizzBuzzSemaphore = new Semaphore(0);
    Semaphore numberSemaphore = new Semaphore(1);
    public FizzBuzz2(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        FizzBuzz2 fizzBuzz = new FizzBuzz2(15);
        new Thread(()->{
            try {
                fizzBuzz.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    // printFizz.run() outputs "fizz".
    public void fizz() throws InterruptedException {
        for (int i = 1; i < n ; i++) {
            if(i%3==0&&i%5!=0){
                fizzSemaphore.acquire();
                System.out.println("fizz");
                releaseAll(i+1);
            }
        }

    }

    // printBuzz.run() outputs "buzz".
    public void buzz() throws InterruptedException {
        for (int i = 1; i < n ; i++) {
            if(i%5==0&&i%3!=0){
                buzzSemaphore.acquire();
                System.out.println("buzz");
                releaseAll(i+1);
            }
        }

    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz() throws InterruptedException {
        for (int i = 1; i < n ; i++) {
            if(i%3 ==0&&i%5==0){
                fizzBuzzSemaphore.acquire();
                System.out.println("fizzbuzz");
                releaseAll(i+1);
            }
        }

    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number() throws InterruptedException {
        for (int i = 1; i < n ; i++) {
            if(!(i%3 ==0||i%5==0)){
                numberSemaphore.acquire();
                System.out.println(i);
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
