package com.wangyg.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

注意：
答案中不可以包含重复的四元组。

示例：
给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 */
public class LeetCode18 {
    @Test
    public void test() {
        Solution s = new Solution();
//      int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        int[] nums = new int[]{-3, -2, -1, 0, 0, 1, 2, 3};
        int target = 0;
        System.out.println(s.fourSum(nums, target).toString());
    }

    /*
        有重复结果生成：有相同元素的直接跳过，需要处理
     */
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new LinkedList<>();
            //首先进行排序
            Arrays.sort(nums);

            //先获取数组的长度
            int n = nums.length;
            for (int i = 0; i < n - 3; i++) {
                if(i>0 && nums[i] == nums[i-1]) continue;

                for(int j= i+1; j<n-2; j++){
                    if(j> i+1 && nums[j] == nums[j-1]) continue;
                    int left  = j+1;
                    int right = n-1;
                    while (left < right){
                        int tmp = nums[i] + nums[j] + nums[left] + nums[right];
                        if(tmp == target){
                            res.add(new LinkedList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right])));
                            //抛出相邻元素时相同的场景
                            while (left < right && nums[left] == nums[left + 1]) {
                                left += 1;
                            }
                            //使用while
                            while (left  < right && nums[right] == nums[right - 1]) {
                                right -= 1;
                            }
                            left += 1;
                            right -= 1;

                        }else if (tmp > target){
                            right--;
                        }else{
                            left++;
                        }
                    }

                }
            }
            return res;
        }
    }


}
