package com.wangyg.leetcode;

import org.junit.Test;

import java.util.Arrays;


/*
    实现获取下一个排列的函数

    概要
        我们需要找到给定数字列表的下一个字典排列，而不是由给定数组形成的数字。


题干的意思是：找出这个数组排序出的所有数中，刚好比当前数大的那个数

比如当前 nums = [1,2,3]。这个数是123，找出1，2，3这3个数字排序可能的所有数，排序后，比123大的那个数 也就是132

如果当前 nums = [3,2,1]。这就是1，2，3所有排序中最大的那个数，那么就返回1，2，3排序后所有数中最小的那个，也就是1，2，3 -> [1,2,3]
 */
public class LeetCode31 {
    @Test
    public void test(){
        Solution solution = new Solution();
        int[] nums = new int[]{1, 2, 3};

        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
    //找到下一个比她更大的，如果当前是最大的，返回最小的
    public class Solution {
        public void nextPermutation(int[] nums) {
            int i = nums.length - 2;
            //一直进行查找，右边小于左边，就一直向左继续查找
            while (i >= 0 && nums[i + 1] <= nums[i]) {
                i--;
            }
            if (i >= 0) {
                int j = nums.length - 1; //j记录最后一个元素的位置
                while (j >= 0 && nums[j] <= nums[i]) { //找到合适与i的位置进行处理
                    j--;
                }
                swap(nums, i, j); //交换i j位置的元素
            }
            //然后进行反转
            reverse(nums, i + 1);
        }
        //从start位置开始 一直到结尾，进行反转 数组
        private void reverse(int[] nums, int start) {
            int i = start, j = nums.length - 1;
            while (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
