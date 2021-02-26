package com.wangzhen.javastudy.jvm.Test;

import com.wangzhen.javastudy.jvm.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 测试异常情况会不会导致jvm 虚拟机退出
 * Datetime:    2021/2/7   下午6:00
 * Author:   王震
 */

@Slf4j
public class TestException {


    /**
     * 主线程 OutOfMemory 并不会导致线程退出,但是其持有的引用可能释放掉。
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        List<byte[]> list = new ArrayList<>(30);
        Thread thread = new Thread(() -> {
            //list.add(null);
            try {
                ServerSocket server = new ServerSocket(8080);
                Socket socket = server.accept();
                // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
                InputStream inputStream = socket.getInputStream();
                byte[] bytes = new byte[1024];
                int len;
                StringBuilder sb = new StringBuilder();
                while ((len = inputStream.read(bytes)) != -1) {
                    //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                    sb.append(new String(bytes, 0, len,"UTF-8"));
                }
                System.out.println("get message from client: " + sb);
                inputStream.close();
                socket.close();
                server.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        },"ioThread");
        thread.start();
        SleepUtils.sleep(10);

        while (true){
            SleepUtils.sleep(1);
            byte [] bytes = new byte[1024*1024*1];
            list.add(bytes);
        }
    }


    /**
     * 异常的抛出并不会终止整个程序
     * @throws IOException
     */
    @Test
    public void testException() throws IOException {
        Thread thread = new Thread(() -> {
            SleepUtils.sleep(5);
            //throw new NullPointerException();
            int i = 5/0;
        });
        thread.start();
        new Thread(()->{
            while (true){

                SleepUtils.sleep(1);
                log.info("hello world");

            }
        }).start();
        System.in.read();
    }

    /**
     * error 依然不会终止程序正常运行
     * @throws IOException
     */
    @Test
    public void testError() throws IOException {
        Thread thread = new Thread(() -> {
            SleepUtils.sleep(5);
            throw new Error();
        });
        thread.start();
        new Thread(()->{
            while (true){

                SleepUtils.sleep(1);
                log.info("hello world");

            }
        }).start();
        System.in.read();
    }

    /**
     *
     * 测试证明堆内存异常也不能导致jvm 退出
     * 只会导致该线程结束运行。
     * -Xms20m -Xmx20m
     * @throws IOException
     */
    @Test
    public void testOutOfMemory() throws IOException {
        Thread thread = new Thread(() -> {
            SleepUtils.sleep(5);
            byte [] bytes = new byte[1024*1024*20];
        });
        thread.start();
        new Thread(()->{

        }).start();
        while (true){

            SleepUtils.sleep(1);
            log.info("hello world");

        }
        //System.in.read();
    }


}
