package com.wangyg.leetcode;

import org.junit.Test;


public class Leetcode202 {
    @Test
    public void test(){
       int n=19;
       Solution solution= new Solution();
        System.out.println(solution.isHappy(n));
    }

    /**
     * 神奇：这都可以使用快慢指针方式，进行处理
     * 一个快指针，走两步 ，所以执行两只操作
     * 一个慢指针走一步： 所以循环中执行一步操作
     * 只要两个不相同就可以一直走
     * 此时有两种情况，如果是神奇数字，则fast指针时1
     * 如果不是神奇数字，则快慢指针会相遇，到时候，并且会相遇
     */
    class Solution {
        public boolean isHappy(int n) {
            //使用快慢指针方式进行处理
            //块慢指针都指向这个数字， 因为题目说都可以死循环，只不过，快乐数字可以循环到1 但是非快乐数不能循环到1
            int slow =n; //使用快慢指针方式，
            int fast =n;
            do{
                //慢指针执行1次
                slow = bitSquareSum(slow);
                //快指针执行两次
                fast = bitSquareSum(fast);
                fast = bitSquareSum(fast);
            }while (slow != fast); //do while条件 快慢指针不相同
            //最后看是否等于1, 如果等于1说明 是快乐数，否则不是快乐数
            return slow== 1;
        }

        /**
         * 获取每一位数的平方  然后sum 求和
         * @param n
         * @return
         */
        int bitSquareSum(int n) {
            //定义一个返回值
            int sum = 0;
            //进行while循环，当n 一直大于0的时候
            while(n > 0)
            {
                int bit = n % 10;  //获取余数
                sum += bit * bit; //进行相乘
                n = n / 10; //改变n的值
            }
            return sum;
        }
    }

    @Test
    public void testSquareSum(){
        int m =10;
        Solution solution = new Solution();
        System.out.println(solution.bitSquareSum(m));
    }
}
