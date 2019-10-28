package com.wangyg.leetcode;

import org.junit.Test;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 */
public class LeetCode53 {
    @Test
    public void test(){
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        Solution solution= new Solution();
        System.out.println(solution.maxSubArray(nums));
    }


    //使用动态规划求出 --动态规划算法求解
    class Solution {
        public int maxSubArray(int[] nums) {
            int max =0;
            int sum =0;

            for (int num : nums) {
                if (sum > 0) {
                    sum+=num;
                }else {
                    sum=num;
                }

                max = Math.max(max, sum);
            }
            return max;
        }
    }
}
