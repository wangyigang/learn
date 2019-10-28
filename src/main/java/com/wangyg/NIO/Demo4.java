package com.wangyg.NIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Demo4 {
    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("demo4write");
        FileInputStream fileInputStream = new FileInputStream("demo4read");

        FileChannel channelRead = fileInputStream.getChannel();
        FileChannel channelWrite = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        while (true) {
            byteBuffer.clear();
            System.out.println(byteBuffer.position());
            int readNumber = channelRead.read(byteBuffer);
            System.out.println(readNumber);
            if (-1 == readNumber) {
                break;
            }
            byteBuffer.flip();
            channelWrite.write(byteBuffer);
        }
        fileOutputStream.close();
        fileInputStream.close();
    }
}
