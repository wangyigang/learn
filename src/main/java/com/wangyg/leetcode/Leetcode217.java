package com.wangyg.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Leetcode217 {
    @Test
    public void test(){
        int[] nums = new int[]{1, 2, 3, 1};
        Solution solution = new Solution();
        System.out.println(solution.containsDuplicate(nums));
    }

    class Solution {
        public boolean containsDuplicate(int[] nums) {
            if (nums == null || nums.length == 0) {
                return false;
            }
            //使用set进行查找
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                if(set.contains(num)){
                    return false;
                }else{
                    set.add(num);
                }
            }
            return true;
        }
    }
}
