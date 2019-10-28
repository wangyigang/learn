package com.wangyg.NIO;

import java.nio.ByteBuffer;

/**
 *  不同数据类型插入到bytebuffer中，需要使用不同的方法, 对应的取出时，也要使用不同的方法
 */
public class Demo5 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.putChar('a');
        buffer.putInt(2);
        buffer.putLong(5000L);
        buffer.putShort((short) 2);
        buffer.putDouble(12.4);
        System.out.println("position="+buffer.position());
        buffer.flip();
        System.out.println(buffer.getChar());
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getDouble());
    }
}
