package com.wangyg.Thread.ch04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Priority {

    private static  volatile  boolean notStart= true;

    private static  volatile  boolean notEnd = true;

    public static void main(String[] args) throws InterruptedException {
        List<Job>  jobs = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            int priority = i<5 ?  Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job, "Thread:" + i);
            thread.setPriority(priority);
            thread.start();
        }
        notStart = false;
        TimeUnit.SECONDS.sleep(10);
        notEnd = false;

        for (Job job : jobs) {
            System.out.println("Job Pritory: "+ job.priority + ", Count: "+ job.jobCount);
        }
        /*
Job Pritory: 1, Count: 4541817
Job Pritory: 1, Count: 4644104
Job Pritory: 1, Count: 4507200
Job Pritory: 1, Count: 4471286
Job Pritory: 1, Count: 4482061
Job Pritory: 10, Count: 5124655
Job Pritory: 10, Count: 5217403
Job Pritory: 10, Count: 5109430
Job Pritory: 10, Count: 5146218
Job Pritory: 10, Count: 5201967
         */
    }

    public static  class Job implements  Runnable{
        //优先级
        private int priority;
        //job个数
        private long jobCount;

        //构造函数
        public Job(int priority) {
            this.priority = priority;
        }



        /**
         * 重新实现run()方法
         */
        @Override
        public void run() {
            while (notStart) {
                Thread.yield();
            }
            while (notEnd) {
                Thread.yield();
                jobCount++;
            }
        }
    }
}
