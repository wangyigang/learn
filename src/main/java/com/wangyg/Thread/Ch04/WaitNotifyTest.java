package com.wangyg.Thread.ch04;

import java.util.concurrent.TimeUnit;

public class WaitNotifyTest {
    static boolean flag = true;

    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        //休眠1s
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    /**
     * waitThread 首先获取对象锁, syn() 这里获取
     */
    static class Wait implements Runnable {

        //重写run方法
        @Override
        public void run() {
            //教唆，拥有lock的monitor
            synchronized (lock) {  //首先获取对象的锁
                //当条件不满足时，继续wait, 同时释放了lcok的锁
                while (flag) { //flag 为true 进入
                    try {
                        System.out.println(Thread.currentThread() + " falg is true  wait");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //条件满足时，完成工作
                System.out.println(Thread.currentThread() + " falg is false ..");
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            //加锁,拥有lock 的monitor
            synchronized (lock) {
                //获取Lock的锁，然后进行通知，通知时不会释放lock的锁
                //知道当前线程释放lock后，waitThread才能 从wait方法中返回
                System.out.println(Thread.currentThread() + " hodl lock ");
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
            //再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() +" hold lock agin. ");
                SleepUtils.second(5);
            }
        }
    }

}
