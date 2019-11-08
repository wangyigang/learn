package com.wangyg.Thread.ch04;

public class SynchronizedTest {
    public static void main(String[] args) {
        //对synTest类进行加锁
        synchronized (SynchronizedTest.class) {

        }
        //静态同步方法, 对synchozedTest Class 对象进行加锁
        m();
    }
    public static  synchronized  void m(){

    }
}
