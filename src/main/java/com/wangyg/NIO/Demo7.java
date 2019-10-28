package com.wangyg.NIO;


import java.nio.ByteBuffer;

public class Demo7 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer=ByteBuffer.allocate(10);
        for(int i=0;i<byteBuffer.capacity();i++){
            byteBuffer.put((byte)i);
        }
        //asReadOnlyBuffer--核心方法
        ByteBuffer byteBuffer1 = byteBuffer.asReadOnlyBuffer(); //创建只读buffer，不能写数据
        System.out.println(byteBuffer.getClass());
        System.out.println(byteBuffer1.getClass());
        byteBuffer1.flip();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer1.position());
        for(int i=0;i<byteBuffer1.capacity();i++){
            System.out.println(byteBuffer1.get());
        }
    }
}
