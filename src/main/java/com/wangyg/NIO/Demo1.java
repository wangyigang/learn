package com.wangyg.NIO;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class Demo1 {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(8); //堆内缓存，受JVM控制的
        for(int i=0; i<buffer.capacity(); i++){
            int nextInt = new SecureRandom().nextInt(20);
            buffer.put(nextInt); //put 8个， 移动position指针
        }
        buffer.flip(); //进行反转指针
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
