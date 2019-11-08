package com.wangyg.Thread.ch04;

import util.Logger;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {
    public static void main(String[] args) {
        //获取java线程管理mxbean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor和synchronizer信息，进货去线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        //遍历线程信息， 金打印线程ID 和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            Logger.info(threadInfo.getThreadId() + ":" + threadInfo.getThreadName());
        }
        /*
 [main|MultiThread.main] |>  6:Monitor Ctrl-Break
 [main|MultiThread.main] |>  5:Attach Listener
 [main|MultiThread.main] |>  4:Signal Dispatcher
 [main|MultiThread.main] |>  3:Finalizer
 [main|MultiThread.main] |>  2:Reference Handler
 [main|MultiThread.main] |>  1:main
         */
    }
}
