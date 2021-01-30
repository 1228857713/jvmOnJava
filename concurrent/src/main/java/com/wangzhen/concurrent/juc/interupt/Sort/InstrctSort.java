package com.wangzhen.concurrent.juc.interupt.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class InstrctSort {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        try{
            //lock.lock();
            for(int i =0;i<10000;i++){
                RecordExample recordExample = new RecordExample();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        recordExample.wirte();
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        recordExample.read();
                    }
                }).start();
            }
        }finally {
            //lock.unlock();
        }

        //System.out.println(RecordExample.sum);
        for(int i=0;i<RecordExample.integerList.size();i++){
            if(RecordExample.integerList.get(i)==0){
                System.out.println(i);
            }
        }

    }
}

class RecordExample{
    static List<Integer> integerList =new ArrayList<>();
    static int sum=0;
    int a =0;
    boolean flag = false;
    public void wirte(){
        a=2;
        flag = true;
    }
    public void read(){
        if(flag){
            integerList.add(a);
        }


    }

}
