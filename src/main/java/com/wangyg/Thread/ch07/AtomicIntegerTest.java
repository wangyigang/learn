package com.wangyg.Thread.ch07;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    static AtomicInteger ai = new AtomicInteger(1);

    //main函数
    public static void main(String[] args) {
        //原始值为1
        System.out.println(ai.getAndIncrement());//首先进行返回当前值，然后进行自加1
        System.out.println(ai.get()); //返回当前值
    }
}
