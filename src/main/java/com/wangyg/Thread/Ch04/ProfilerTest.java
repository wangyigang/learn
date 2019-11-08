package com.wangyg.Thread.ch04;

import java.util.concurrent.TimeUnit;

public class ProfilerTest {
    private static  final ThreadLocal<Long> TIME_THREADLOCAL= new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static  final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    /**
     * 面向切面编程过程中 AOP 中， 可以在方法调用前的切入点执行begin()方法，而在方法调用后的切入点执行end()方法
     * 这样依旧可以获取方法的执行耗时
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ProfilerTest.begin(); //开始
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + ProfilerTest.end() + " mills");
    }
}
