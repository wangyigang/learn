package com.wangyg.leetcode;

import org.junit.Test;

public class Leetcode191 {
    @Test
    public void test(){
        int n = 1011;
        Solution solution = new Solution();
        System.out.println(solution.hammingWeight(n));
    }


    public class Solution {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int cnt=0;
            while (n!=0){
                if ((n & 1) == 1) {
                    cnt++;
                }
               n>>>=1;
            }
            return cnt;
        }
    }

}
