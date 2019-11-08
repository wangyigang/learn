package com.wangyg.Thread.ch04;

import java.util.concurrent.TimeUnit;

public class Shutdown {
    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread countThread = new Thread(one, "countThread");
        countThread.start();

        //睡眠1秒，main线程对CountThread进行终端，使countThread 能够感知中断而结束
        TimeUnit.SECONDS.sleep(1);
        // 终端操作
        countThread.interrupt(); //中断操作

        Runner two = new Runner();
        countThread = new Thread(two, "CountTHread");

        countThread.start();

        //睡眠1秒， main线程对Runner two进行取消，使得CountThread 能够感知on 为false而结束
        TimeUnit.SECONDS.sleep(1);
        two.cancel(); //调用线程的cancel 进行取消操作也可以
    }

    private static  class Runner implements  Runnable{
        private long i;
        //volatile变量
        private volatile  boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("count i=" + i);
        }

        /**
         * cancel操作
         */
        public void cancel(){
            on = false;
        }
    }
}
