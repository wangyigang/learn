package com.wangyg.BIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BioServerHandler implements Runnable {
    //负责客户端通信
    private Socket socket; //客户端通信

    public BioServerHandler(Socket socket) {
        this.socket = socket;
    }

    /*
        线程的run方法
     */
    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = socket.getInputStream(); //inputStream 读取数据的
            outputStream = socket.getOutputStream(); //outputStream 向外写出数据的
            int count = 0;
            String content = null; //内容
            byte[] bytes = new byte[1024];

            while ((count = inputStream.read(bytes)) != -1) { //通过read方法读取数据, count ： 读取的字节数
                String line = new String(bytes, 0, count, "utf-8");
                System.out.println(line);
                //如果是SJ -时间 那么返回当前时间， 否则返回 你发的啥
                content = line.trim().equalsIgnoreCase("SJ") ? new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) : "你发的啥?";
                outputStream.write(content.getBytes()); //将content写出去
                outputStream.flush(); //刷写,从缓存中返回给客户端
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally { //进行关闭资源
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!= null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
