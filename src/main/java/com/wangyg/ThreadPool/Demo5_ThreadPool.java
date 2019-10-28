package com.wangyg.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo5_ThreadPool {

    public static void main(String[] args) throws InterruptedException {
        //线程复用，节省资源，
        ExecutorService service = Executors.newFixedThreadPool(5);//使用executors工具类创建固定个数线程池
        for (int i = 0; i < 6; i++) {
            //execute() 进行创建任务
            service.execute(()->{
                try {
                    //微秒
                    TimeUnit.MICROSECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }) ;
        }
        //输出当前线程的状态
        System.out.println(service);
//        java.util.concurrent.ThreadPoolExecutor@6193b845[Running, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]

        //shutdownNow() //关闭线程池，
//        service.shutdownNow();
        service.shutdown(); //进行关闭
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());  //判断是否shutdown()
        System.out.println(service);

        TimeUnit.SECONDS.sleep(5);
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);
    }
}
