package com.wangyg.leetcode;

import org.junit.Test;

public class Leetcode136 {
    @Test
    public void test(){
        int[] nums = new int[]{4,1,2,1,2};

        Solution solution = new Solution();

        System.out.println(solution.singleNumber(nums));

    }

    //只出现一次的数字
    class Solution {
        public int singleNumber(int[] nums) {
            //使用一个变量进行记录
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                res ^=nums[i];
            }
          return  res;
        }
    }
}
