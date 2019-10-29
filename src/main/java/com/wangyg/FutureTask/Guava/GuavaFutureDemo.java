package com.wangyg.FutureTask.Guava;

import com.google.common.util.concurrent.*;
import util.Logger;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * 使用guava方式进行处理任务
 */
public class GuavaFutureDemo {
    public static final int SLEEP_GAP = 500;

    /**
     * 获取当前线程名称
     *
     * @return
     */
    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    /**
     * 烧水线程
     */
    static class HotWarterJob implements Callable<Boolean> {
        @Override
        public Boolean call() throws Exception {

            try {
                Logger.info("洗好水壶");
                Logger.info("灌上凉水");
                Logger.info("放在火上");

                //线程睡眠一段时间，代表烧水中
                Thread.sleep(SLEEP_GAP);
                Logger.info("水开了");

            } catch (InterruptedException e) {
                Logger.info(" 发生异常被中断.");
                return false;
            }
            Logger.info(" 烧水工作，运行结束.");

            return true;
        }
    }

    /**
     * 洗杯子线程
     */
    static class WashJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            try {
                Logger.info("洗茶壶");
                Logger.info("洗茶杯");
                Logger.info("拿茶叶");
                //线程睡眠一段时间，代表清洗中
                Thread.sleep(SLEEP_GAP);
                Logger.info("洗完了");

            } catch (InterruptedException e) {
                Logger.info(" 清洗工作 发生异常被中断.");
                return false;
            }
            Logger.info(" 清洗工作  运行结束.");
            return true;
        }
    }


    //泡茶线程

    /**
     * 主线程
     */
    static class MainJob implements Runnable { //实现runnable  的一个target
        boolean warterOk = false;
        boolean cupOk = false;
        int gap = SLEEP_GAP / 10;

        @Override
        public void run() {
            //死循环，一致在尝试
            while (true) {
                try {
                    Thread.sleep(gap);
                    Logger.info("读书中......");
                } catch (InterruptedException e) {
                    Logger.info(getCurThreadName() + "发生异常被中断.");
                }

                if (warterOk && cupOk) {
                    drinkTea(warterOk, cupOk);
                }
            }
        }

        public void drinkTea(Boolean wOk, Boolean cOK) {
            if (wOk && cOK) {
                Logger.info("泡茶喝，茶喝完");
                this.warterOk = false;
                this.gap = SLEEP_GAP * 100;
            } else if (!wOk) {
                Logger.info("烧水失败，没有茶喝了");
            } else if (!cOK) {
                Logger.info("杯子洗不了，没有茶喝了");
            }

        }
    }

    /**
     * 入口程序
     *
     * @param args
     */
    public static void main(String args[]) {
        //新奇一个线程，作为泡茶主线程
        MainJob mainJob = new MainJob();
        Thread mainThread = new Thread(mainJob); //Runnable target
        mainThread.setName("主线程");
        mainThread.start(); //启动主线程

        //烧水的业务逻辑
        Callable<Boolean> hotJob = new HotWarterJob();
        //清洗的业务逻辑
        Callable<Boolean> washJob = new WashJob();

        //创建java 线程池
        ExecutorService jPool = Executors.newFixedThreadPool(10);
        //包装java线程池，构建guava线程池
        ListeningExecutorService gPool = MoreExecutors.listeningDecorator(jPool); //guava对线程池进行装饰
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //提交烧水的业务逻辑，得到异步任务
        ListenableFuture<Boolean> hotFuture = gPool.submit(hotJob);
        //绑定任务执行完成后的回调，到异步任务
        Futures.addCallback(hotFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    mainJob.warterOk = true;
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Logger.info("烧水失败，没有茶喝了");
            }
        });

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //提交清洗的业务逻辑，取到异步任务
        ListenableFuture<Boolean> washFuture = gPool.submit(washJob); //提交洗茶杯任务
        //绑定任务执行完成后的回调，到异步任务
        Futures.addCallback(washFuture, new FutureCallback<Boolean>() {
            public void onSuccess(Boolean r) {
                if (r) {
                    mainJob.cupOk = true;
                }
            }

            public void onFailure(Throwable t) {
                Logger.info("杯子洗不了，没有茶喝了");
            }
        });
    }

}
