package com.wangyg.leetcode;

import org.junit.Test;

/*
给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数 dividend 除以除数 divisor 得到的商。

示例 1:

输入: dividend = 10, divisor = 3
输出: 3
示例 2:

输入: dividend = 7, divisor = -3
输出: -2
说明:

被除数和除数均为 32 位有符号整数。
除数不为 0。
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 */
public class LeetCode29 {
    @Test
    public void test(){
        int  dividend = 10;
        int divisor = 3;
        //输出: 3
        Solution s = new Solution();
        System.out.println(s.divide(10, 3));
        System.out.println(s.divide(7, -3));
        //-2147483648
        //-1
        System.out.println(s.divide(-2147483648, -1));
        System.out.println(Integer.MIN_VALUE);
    }

    class Solution {
        public int divide(int dividend, int divisor) {
            int i = dividend / divisor;
            return i;
        }
    }

}
