package com.wangyg.NIO;

import java.nio.ByteBuffer;

public class Demo9 {

    public static void main(String[] args) throws Exception {
        byte[] bytes=new byte[]{'a','b','c'};
        //wrap
        ByteBuffer byteBuffer=ByteBuffer.wrap(bytes);// 包装，使用原生数据包装生成的， 可以修改原始数据，也会影响ByteBuffer
        bytes[0]='b';
        byteBuffer.put(2,(byte)'b');
        for(int i=0;i<byteBuffer.capacity();i++){
            System.out.println((char)byteBuffer.get());
        }
    }
}
