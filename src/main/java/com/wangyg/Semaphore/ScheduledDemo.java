package com.wangyg.Semaphore;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduledDemo {
    public static void main(String[] args) {
        //创建可调度的线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        for(int i=0; i<10; i++){
            Runnable task = new TaskDemo();
            pool.execute(task);
        }

        //关闭
        pool.shutdown();
    }
}
