package com.wangyg.BIO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 */
public class BioClient {

    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            //创建客户端socket
            socket = new Socket("127.0.0.1", 9999);
            new Thread(new BioClientHandler(socket)).start(); //循环读取 --开启一个线程一直进行循环读取

            outputStream = socket.getOutputStream();
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入要发送的消息：");
            while (true){
                String s = scanner.nextLine();
                if(s.trim().equalsIgnoreCase("by")){
                    break;
                }
                outputStream.write(s.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
