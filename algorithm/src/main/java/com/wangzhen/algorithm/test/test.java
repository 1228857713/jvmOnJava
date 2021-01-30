package com.wangzhen.algorithm.test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class test {
    public static void main(String[] args){
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
        List objects = new CopyOnWriteArrayList<>();
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(1);


    }
}
