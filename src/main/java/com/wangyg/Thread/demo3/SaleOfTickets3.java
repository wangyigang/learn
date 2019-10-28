package com.wangyg.Thread.demo3;

import java.util.LinkedList;
import java.util.List;

public class SaleOfTickets3 {
    private static List<Integer> tickets = new LinkedList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add(i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(true){
                    //这里使用synchronized，使两个操作具备了原子性，不会出问题
                    synchronized(tickets){
                        if(tickets.size() <= 0){
                            break;
                        }
                        System.out.println("销售票编号:" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
