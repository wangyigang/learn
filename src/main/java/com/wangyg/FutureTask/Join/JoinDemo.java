package com.wangyg.FutureTask.Join;

import util.Logger;

/**
 * 程序中有三个线程:
 * 主线程 main
 * 烧水线程hThread
 * 清洗线程 wThread
 *
 */
public class JoinDemo {
    public static final int SLEEP_GAP=500;

    /**
     * 获取当前线程的名字
     * @return
     */
    public static  String getCurThreadName(){
        return Thread.currentThread().getName();
    }

    /**
     * 烧水线程
     */
    static  class HotWaterThread extends  Thread{
        //烧水线程
        public HotWaterThread() {
            super("** 烧水-Thread");
        }
        public void run(){
            try {
                Logger.info("洗好水壶");
                Logger.info("灌上凉水");
                Logger.info("放在火上");
                //线程睡眠一段时间,表示烧水中
                Thread.sleep(SLEEP_GAP);
                Logger.info("水开了");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Logger.info("发生异常被中断");
            }
            Logger.info("运行结束");
        }
    }

    static  class WashThread extends  Thread{
        public WashThread() {
            super("$$ 清洗-Thread");
        }
        public  void run(){
            try {
                Logger.info("洗茶壶");
                Logger.info("洗茶杯");
                Logger.info("拿茶叶");
                //线程睡眠一段时间，代表清洗中
                Thread.sleep(SLEEP_GAP);
                Logger.info("洗完了");
            } catch (InterruptedException e) {
                Logger.info("发生异常 被中断");
            }
            Logger.info("运行结束.");
        }
    }

    public static void main(String[] args) {
        Thread hThread= new HotWaterThread(); //烧水线程
        Thread wThread = new WashThread(); //洗水杯线程

        hThread.start();
        wThread.start();
        try {
            // 合并烧水-线程
            hThread.join(); //使用join进行加塞线程
            // 合并清洗-线程
            wThread.join(); //使用join进行加塞线程

            Thread.currentThread().setName("主线程");
            Logger.info("泡茶喝");

        } catch (InterruptedException e) {
            Logger.info(getCurThreadName() + "发生异常被中断.");
        }
        Logger.info(getCurThreadName() + " 运行结束.");
    }
}
