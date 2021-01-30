package com.wangzhen.netty.bio;



import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: bio app
 * Datetime:    2020/9/21   6:46 下午
 * Author:   王震
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了");
        while (true){
            System.out.println("等待连接");
            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });

        }
    }

    public static void handler(Socket socket){
        try {

            byte[] bytes = new byte[1024] ;
            InputStream inputStream = socket.getInputStream();
            while (true){
                System.out.println("当前线程id为"+Thread.currentThread().getId()+"   线程名字为"+
                        Thread.currentThread().getName());
                int read = inputStream.read(bytes);
                if(read!=-1){
                    // 打印接收到的字符串
                    System.out.println(new String(bytes,0,read));
                }else{
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭客户端连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
