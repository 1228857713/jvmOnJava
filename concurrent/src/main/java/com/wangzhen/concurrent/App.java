package com.wangzhen.concurrent;


import org.junit.Test;
import sun.net.ftp.impl.FtpClient;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Description: 源码编译步骤:
 *                         1. javac App.java  --> 生成class 文件
 *                         2. java App  -->执行 App.class 注意不要带 .class 后缀
 * Datetime:    2020/9/29   16:48
 * Author:   王震
 */
public class App {
    public static void main(String[] args) throws IOException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2000, 10000, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
        threadPoolExecutor.prestartAllCoreThreads();
        System.in.read();


    }

    @Test
    public void test02(){


    }

    @Test
    public void test01(){
      String str =  "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxotherweb.csebank.cn\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxotherweb.csebank.cn\n" +
              "testlxotherweb.csebank.cn\n" +
              "testlxotherweb.csebank.cn\n" +
              "testlxotherweb.csebank.cn\n" +
              "testlxotherweb.csebank.cn\n" +
              "testlxotherweb.csebank.cn\n" +
              "testlxotherweb.csebank.cn\n" +
              "testlxotherweb.csebank.cn\n" +
              "testlxotherweb.csebank.cn\n" +
              "testlxotherweb.csebank.cn\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxcwxt.csebank.com\n" +
              "testlxotherweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxotherapp.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n" +
              "testlxproweb.csebank.cn\n";
        String[] urls = str.split("\n");
        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < urls.length; i++) {
            hashSet.add(urls[i]);
        }
        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }
}