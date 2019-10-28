package com.wangyg.NIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Demo2 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("demo2");
        //filechannel 没有注册的方法
        FileChannel channel = fileInputStream.getChannel(); // 转成NIO方式，获取NIO的channel
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            byteBuffer.clear(); // 每次进行清空
            int read = channel.read(byteBuffer);
            if (read == -1) {
                break;
            }
            System.err.println(read);
            String string = new String(byteBuffer.array());
            System.out.println(string);
        }
        fileInputStream.close();
    }
}
