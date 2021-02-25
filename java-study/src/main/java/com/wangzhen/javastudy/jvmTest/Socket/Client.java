package com.wangzhen.javastudy.jvmTest.Socket;

/**
 * Description:
 * Datetime:    2021/2/7   下午7:51
 * Author:   王震
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        //客户端
        Socket s = null;
        try {
            s = new Socket("127.0.0.1", 10086);
            OutputStream output = s.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(output);
            BufferedWriter bw = new BufferedWriter(osw);

            Scanner can = new Scanner(System.in);
            String string = can.next();
            bw.write(string);
            bw.flush();
            System.out.println("发送完成........");
            s.shutdownOutput();
//            InputStream input = s.getInputStream();
//            InputStreamReader in = new InputStreamReader(input);
//            BufferedReader br = new BufferedReader(in);
//            System.out.println(br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(s != null){
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
