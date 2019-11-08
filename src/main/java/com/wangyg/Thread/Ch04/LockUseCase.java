package com.wangyg.Thread.ch04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockUseCase {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        //不要将获取锁的过程写在try 块中，因为如果在获取锁时，发生了异常，会导致锁无故释放
        lock.lock();
        //使用try catch方式进行释放锁，一定要记得释放锁
        try {
            System.out.println("thread" + System.currentTimeMillis());
        }finally {
            lock.unlock();
        }
    }
}
