package com.wangyg.BIO;

import java.util.concurrent.*;

/*
    实现Executor接口
 */
public class TimeServerHandlerExecutorPool implements Executor {

    //ExecutorService
    private ExecutorService executorService;

    public TimeServerHandlerExecutorPool(int maxPoolSize, int queueSize) {
        /**
         * corePoolSize : 核心线程数量
         * maximumPoolSize: 线程创建最大数量
         * keepAliveTiume:
         */
        this.executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
    }

    @Override
    public void execute(Runnable command) {
        executorService.execute(command); //执行传进来的命令
    }

    public static void main(String[] args) {
        TimeServerHandlerExecutorPool timeServerHandlerExecutorPool = new TimeServerHandlerExecutorPool(0, 20);
        //死循环执行任务
        while (true){
            timeServerHandlerExecutorPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("aaaaa");
                }
            });
        }
    }
}
