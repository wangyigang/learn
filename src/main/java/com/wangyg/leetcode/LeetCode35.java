package com.wangyg.leetcode;

import org.junit.Test;


/*
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。

示例 1:

输入: [1,3,5,6], 5
输出: 2
示例 2:

输入: [1,3,5,6], 2
输出: 1
示例 3:

输入: [1,3,5,6], 7
输出: 4
示例 4:

输入: [1,3,5,6], 0
输出: 0
 */

//二分查找:改进版
public class LeetCode35 {
    @Test
    public void test(){
        Solution solution = new Solution();
         int[] nums = new int[]{1};
         //[1,3,5,6]

        int target = 1;
        System.out.println(solution.searchInsert(nums, target));
    }

    class Solution {
        public int searchInsert(int[] nums, int target) {
            int ret = -1;

            //首先进行二分查找
            int left = 0;
            int right = nums.length-1;
            int mid = (left+right)/2;
            while (left <= right) {
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid+1;
                    mid = (left+right) /2;
                }else{
                    right = mid-1;
                    mid =(left+right) /2;
                }
            }


            if (target < nums[0]) {
                return 0;
            }
            if (target > nums[nums.length - 1]) {
                return nums.length;
            }
            //如果在没有找到的情况下，找到合适的位置进行插入
            for (int i = 0; i < nums.length-1; i++) {
                if (target > nums[i] && nums[i+1] > target ) {
                    return i+1;
                }
            }

            return ret;
        }
    }
}
