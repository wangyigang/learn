package com.wangyg.leetcode;

import org.junit.Test;

import java.util.Stack;

public class Leetocde155 {

    @Test
    public void  test(){

    }


    class MinStack {

        //使用栈进行存储
        Stack<Integer> stack= new Stack<>();
        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int x) {
            this.stack.push(x);
           //可能出现出栈情况下，最小值会进行改变，所以需要进行逻辑处理
        }

        public void pop() {
            this.stack.pop();
        }

        public int top() {
            return this.stack.peek();
        }

        public int getMin() {
            int min= Integer.MAX_VALUE;
            for (Integer integer : stack) {
                if (min > integer) {
                    min=integer;
                }
            }

            return min;
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}
