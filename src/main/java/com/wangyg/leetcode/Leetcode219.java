package com.wangyg.leetcode;

import org.junit.Test;
import util.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode219 {
    @Test
    public void test(){
        int[] nums = new int[]{1, 2, 3, 1, 2, 3};
        int k = 2;

        Solution solution = new Solution();
        System.out.println(solution.containsNearbyDuplicate(nums, k));
    }


    class Solution {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; ++i) {
                if (set.contains(nums[i])) {return true;}

                set.add(nums[i]);
                if (set.size() > k) {
                    set.remove(nums[i - k]);
                }
            }
            return false;
        }
    }
}
