package com.wangyg.Thread.Ch04;

public class Interrupted {
    public static void main(String[] args) {
        Thread sleepThread =  new Thread(new SleepRunner());
    }


    private static class SleepRunner {


    }
}
