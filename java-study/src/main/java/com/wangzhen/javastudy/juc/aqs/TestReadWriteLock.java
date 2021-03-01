package com.wangzhen.javastudy.juc.aqs;

import com.wangzhen.javastudy.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;



@Slf4j
public class TestReadWriteLock {

    @Test
    public void test1() throws IOException {
        DataContainer dataContainer = new DataContainer();
        new Thread(()->{
            dataContainer.read();
        },"t1").start();
        new Thread(()->{
            dataContainer.read();
            //dataContainer.write(new Object());
        },"t2").start();
        System.in.read();
    }
}

@Slf4j
class DataContainer{

    public Object data;
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = rw.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = rw.writeLock();
    public static void main(String[] args) {

    }
    public Object read(){
        log.info("获取读锁");
        try{
            readLock.lock();
            log.info("开始读取数据");
            SleepUtils.second(1);
            return data;
        }finally {
            log.info("释放读锁");
           readLock.unlock();
        }
    }
    public void write(Object obj){
        log.info("获取写锁");
        try{
            writeLock.lock();
            log.info("开始写数据");
            data=obj;
        }finally {
            log.info("释放写锁");
            writeLock.unlock();
        }
    }


}