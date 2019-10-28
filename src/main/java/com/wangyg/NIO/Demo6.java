package com.wangyg.NIO;

import java.nio.ByteBuffer;

public class Demo6 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte) i);  //强转为byte,然后 放入byteBuffer中
        }
        byteBuffer.position(2);  //将postition 位置 10 设置为2
        byteBuffer.limit(8);
        ByteBuffer resetBuffer = byteBuffer.slice(); //复制一个byteBuffer, 共享的，会影响原生的数组
        System.out.println("position="+resetBuffer.position());
        System.out.println("limit="+resetBuffer.limit());
        System.out.println("limit="+resetBuffer.capacity());
        for (int i = 0; i < resetBuffer.capacity(); i++) {
            byte anInt = resetBuffer.get();
            resetBuffer.put(i, (byte) (anInt * 2));
        }
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
        while (byteBuffer.hasRemaining()) {
            System.out.println(byteBuffer.get());
        }
    }
}
