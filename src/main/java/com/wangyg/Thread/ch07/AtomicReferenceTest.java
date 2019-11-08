package com.wangyg.Thread.ch07;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    public static AtomicReference<User> atomicReference = new AtomicReference();


    static class User{
        private String name;
        private int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName(){
            return this.name;
        }

        public int getOld(){
            return old;
        }
    }

    public static void main(String[] args) {

    }
}
