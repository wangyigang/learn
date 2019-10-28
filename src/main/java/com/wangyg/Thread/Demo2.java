package com.wangyg.Thread;

import java.util.concurrent.TimeUnit;

public class Demo2 {
    String s1 = "hello";
    String s2 = "hello";

    public void test1(){
        synchronized (s1) {
            System.out.println("t1 start...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("t1 end...");
        }
    }

    public void test2(){
        synchronized (s2) {
            System.out.println("t2 start...");
        }
    }

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        new Thread(demo2::test1, "test1").start();
        new Thread(demo2::test2, "test2").start();
    }
}
