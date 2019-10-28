package com.wangyg.leetcode;

import org.junit.Test;

public class LeetCode70 {
    @Test
    public void test() {
        int n = 3;
        Solution solution = new Solution();
        System.out.println(solution.climbStairs(n));
    }

    //使用递归方式进行处理，效率较低
    public class Solution {
        public int climbStairs(int n) {
            return climb_Stairs(0, n);
        }
        public int climb_Stairs(int i, int n) {
            if (i > n) {
                return 0;
            }
            if (i == n) {
                return 1;
            }
            return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
        }
    }
    //爬楼梯
    //使用递归方式进行处理
    class Solution2 {
        public int climbStairs(int n) {
            if(n <= 2){
                return n;
            }
            int num1 =  1;
            int num2 = 2;
            int numN = 0;
            for(int i = 2;i < n;i ++){
                numN = num1 + num2;
                num1 = num2;
                num2 = numN;
            }
            return numN;
        }
    }
}
