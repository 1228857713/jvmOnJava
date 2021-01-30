package com.wangzhen.concurrent.pattern.保护性暂停;

import com.wangzhen.concurrent.util.SleepUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuardedSuspensionTimeout {
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

class GuardedObjectTimeout{
    final Object lock = new Object();
    public Object response=null;
    public Object get(long millis){
        long currentTime = System.currentTimeMillis();
        long waitTime=0;
        synchronized (lock){
            while (response==null){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitTime = System.currentTimeMillis() - currentTime;
                // 等待时间超过最长时间直接退出
                if(waitTime>=millis){
                    break;
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
