package com.lesson_tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClientDemo01 {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream os = null;

        try {
            // 1. 要知道服务器的地址,端口号
            InetAddress serverIP = InetAddress.getByName("127.0.0.1");
            int port = 9999;
            // 2. 创建一个socket链接
            socket = new Socket(serverIP,port);
            // 3. 发送消息IO流
            os = socket.getOutputStream();
            os.write("你好！".getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
