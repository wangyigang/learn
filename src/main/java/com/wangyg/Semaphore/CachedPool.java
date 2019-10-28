package com.wangyg.Semaphore;

import java.security.Policy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedPool {
    public static void main(String[] args) {
        //创建缓冲性质的线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        //创建任务
        for(int i=0; i<10; i++){
            //创建任务
            Runnable task = new TaskDemo();
            //将任务交给pool线程池执行
            pool.execute(task);
        }

        //关闭线程池
        pool.shutdown();
    }
}
