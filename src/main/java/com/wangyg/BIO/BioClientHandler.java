package com.wangyg.BIO;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class BioClientHandler  implements  Runnable{
    //套接字
    private Socket socket;

    /**
     * 构造函数
     */
    public BioClientHandler(Socket socket){
        this.socket = socket;
    }

    /**
     * run方法
     */
    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            //获取输入流
            inputStream = socket.getInputStream(); //接收服务器端返回的信息

            int count = 0;

            byte[] bytes = new byte[1024];

            while ((count = inputStream.read(bytes)) != -1) {
                System.out.println("\n收到服务器端的信息: " + new String(bytes, 0, count, "utf-8"));
                System.out.println("请输入要发送的消息: ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
