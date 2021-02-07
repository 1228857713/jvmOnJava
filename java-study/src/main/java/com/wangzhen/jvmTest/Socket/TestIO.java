package com.wangzhen.jvmTest.Socket;

import lombok.extern.slf4j.Slf4j;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description:
 * Datetime:    2021/2/7   下午7:42
 * Author:   王震
 */
@Slf4j
public class TestIO {

    public void test01() throws Exception {
        ServerSocket serverSocket = new ServerSocket(7071);
        serverSocket.accept();
        while (true) {
            Socket accept = serverSocket.accept();
            Thread thread = new Thread(() -> {

            });
            thread.start();
        }
    }
}
