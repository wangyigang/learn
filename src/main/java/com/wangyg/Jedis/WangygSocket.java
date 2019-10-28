package com.wangyg.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WangygSocket {
    //socket套接字
    private Socket socket;
    //输入流
    public InputStream inputStream;
    //输出流
    public OutputStream outputStream;

    /**
     * 构造函数
     */
    public WangygSocket(String ip, int port){
        try {
            socket = new Socket(ip, port);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送信息函数
     * @param str
     */
    public void send(String str) {
        try {
            outputStream.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取
     * @return
     */
    public String read(){
        byte[] bytes = new byte[1024];
        int count=0;
        try {
            count = inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes, 0, count);
    }
}
