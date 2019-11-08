package com.wangyg.Thread.ch04;

import java.util.concurrent.TimeUnit;

public class Interrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread =  new Thread(new SleepRunner(),"test");
        sleepThread.setDaemon(true);
        //不停的进行运转
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        System.out.println("sleetThread interrunpted is: " + sleepThread.isInterrupted());

        System.out.println("busyThread interrupted is"+ busyThread.isInterrupted());

        SleepUtils.second(2);
    }


    private static class SleepRunner implements  Runnable {

        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);

            }
        }
    }

    static  class BusyRunner implements  Runnable{

        @Override
        public void run() {
            while (true) {

            }
        }
    }
}
