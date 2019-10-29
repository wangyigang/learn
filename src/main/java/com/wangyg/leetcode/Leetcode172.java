package com.wangyg.leetcode;

import org.junit.Test;

public class Leetcode172 {
    @Test
    public void test(){
        int n=5;
        Solution solution = new Solution();
        System.out.println(solution.trailingZeroes(n));
    }


    class Solution {
        //是一道数学问题，
        public int trailingZeroes(int n) {
            int count = 0;
            while (n >= 5) {
                count += n / 5;
                n /= 5;
            }
            return count;
        }
    }


}
