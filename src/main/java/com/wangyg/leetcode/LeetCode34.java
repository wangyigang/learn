package com.wangyg.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class LeetCode34 {
    @Test
    public void test() {
        //nums = [5,7,7,8,8,10], target = 8

        //nums = [5,7,7,8,8,10],
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 6;
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.searchRange(nums, target))); //[3,4]
    }

    //找到target在数组的开始位置和结束为止 升序数组中, 不存在返回[-1,-1]
    /*
        要求： 时间复杂度为O lg(n)
        二分查找:
            二分查找+ 向左和有进行查找
     */
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int[] ret = new int[]{-1, -1};

            int left = 0;
            int right = nums.length - 1;
            int mid = (left + right) / 2;

            while (left <= right) {
                if (nums[mid] == target) {
                    //向左和右进行查找
                    //首先进行赋值
                    ret[0] = mid;
                    ret[1] = mid;

                    //向左和有进行查找
                    search(nums, ret, mid, target);
                    return ret;
                } else if (nums[mid] < target) {
                    left = mid +1;
                    mid = (left + right) / 2;
                } else {
                    right = mid-1;
                    mid = (left + right) / 2;
                }

            }
            return ret;
        }

        //向左和有进行查找
        private void search(int[] nums, int[] ret, int mid, int target) {
            int left = mid;
            int right = mid;

            while (left >=0 && nums[left] == target) {
                left--;
            }
            ret[0] = left+1;
            while (right <= nums.length - 1 && nums[right] == target) {
                right++;
            }
            ret[1] = right-1;
        }
    }
}
