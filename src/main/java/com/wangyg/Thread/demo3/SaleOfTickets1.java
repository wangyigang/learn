package com.wangyg.Thread.demo3;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class SaleOfTickets1 {
    private static Vector<Integer> tickets = new Vector<>();
    //静态方法块
    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add(i);
        }
    }

    //进行卖票
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (tickets.size() > 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "卖票" + tickets.remove(0));
                }
            }).start();
        }
    }
}
