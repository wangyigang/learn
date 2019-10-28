package com.wangyg.Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedPoolDemo {
    public static void main(String[] args) {
        //固定大小的线城市
        ExecutorService pool = Executors.newFixedThreadPool(5);
        //创建10个任务给Pool线程池
        for(int i=0; i<10; i++){
            //创建任务task
            Runnable task = new TaskDemo();
            //将任务交给线程池
            pool.execute(task); //线程池执行任务
        }
        //执行完按成后，关闭线程池
        pool.shutdown(); //关闭
    }
}


