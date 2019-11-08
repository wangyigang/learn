package com.wangyg.Thread.ch07;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {
    static int[] value = new int[]{1, 2};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value); //构建原子数组的时候，需要先出按键一个普通数组

    //主入口
    public static void main(String[] args) {
        ai.getAndSet(0, 3); //i 是索引，对指定位置的索引设置新元素
        System.out.println(ai.get(0));//3
        System.out.println(value[0]);  //1
        //value 中的值没有变，改变的只是原子类包含的数组
    }
}
