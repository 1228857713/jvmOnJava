package com.wangzhen.javastudy.designPattern.保护性暂停;

import com.wangzhen.javastudy.util.SleepUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuardedSuspension {
    static Logger logger = LoggerFactory.getLogger(GuardedSuspension.class);
    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(()->{
            Object object=download();
            logger.debug("下载完成....");
            guardedObject.complete(object);

        }).start();
        logger.debug("waiting.....");
        Object obj=guardedObject.get();
        System.out.println(obj.toString());
    }

    public static Object download(){
        // 等待5s 代表成功
        SleepUtils.second(5);
        return new Object();
    }
}

class GuardedObject{
    final Object lock = new Object();
    public Object response=null;
    public Object get(){
        synchronized (lock){
            while (response==null){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return response;
    }
    public void complete(Object obj){
        synchronized(lock){
            this.response=obj;
            lock.notifyAll();
        }
    }
}
