package com.wangyg.Semaphore;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//创建任务线程
public class TaskDemo implements  Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is running...");
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
