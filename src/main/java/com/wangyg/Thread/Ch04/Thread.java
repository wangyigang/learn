package com.wangyg.Thread.Ch04;

import java.security.AccessControlContext;

public class Thread {

    public static void main(String[] args) {

    }

    private  void init (ThreadGroup g, Runnable target, String name, long stackSize, AccessControlContext acc){
        if(name ==null){
            throw new NullPointerException("name connot be null");
        }
        //当前线程就是该线程的福线程
        Thread parent = currentThread();
    }

    private Thread currentThread() {
        return null;
    }
}
