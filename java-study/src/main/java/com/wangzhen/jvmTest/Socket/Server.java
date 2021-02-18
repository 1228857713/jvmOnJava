package com.wangzhen.jvmTest.Socket;

/**
 * Description:
 * Datetime:    2021/2/7   下午7:51
 * Author:   王震
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Server {
    //服务端
    public static void main(String[] args) {
        ServerSocket ss = null;
        BufferedWriter bw = null;
        Date date = new Date();
        SimpleDateFormat simp = new SimpleDateFormat("yyyy年 MM月 H点  mm分 ss秒");
        String time = simp.format(date);
        try {
            ss = new ServerSocket(10086);
            while(true){
                System.out.println("^-^ 服务端已开启 ^-^\r\n");
                Socket socket = ss.accept();//监听客户端发送的socket对象，在接收到客户端请求之前处于阻塞状态

                InputStream is = socket.getInputStream();//从socket中读取传输的内容
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                File file = new File("f:\\net.txt");
                bw = new BufferedWriter(new FileWriter(file,true));

                String str = null;
                while((str = br.readLine())!= null){
                    if(file.exists()){
                        file.mkdirs();
                        bw.write(str);
                        bw.newLine();
                        bw.flush();
                    }
                    System.out.println("来自客户端的消息 :\r\n"+str +"\r\n\t"+ time);
                }

                OutputStream output = socket.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

                @SuppressWarnings("resource")
                Scanner can = new Scanner(System.in);
                String string = can.next();
                writer.write(string);
                writer.flush();

                socket.shutdownInput();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(ss != null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
