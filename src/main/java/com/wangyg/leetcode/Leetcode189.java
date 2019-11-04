package com.wangyg.leetcode;

import org.junit.Test;
import java.util.Arrays;

public class Leetcode189 {
    @Test
    public void test(){
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k=3;
        System.out.println(Arrays.toString(nums));

        Solution solution = new Solution();
        solution.rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }
    //旋转一位数组
    class Solution {
        public void rotate(int[] nums, int k) {
            if(nums == null) return;

            //保存最后一个值，然后整体向后移动，将最后一个放在第一个
            for (int i = 0; i < k; i++) {
                int tmp = nums[nums.length - 1];
                System.arraycopy(nums, 0, nums, 1, nums.length-1);
                //填值
                nums[0] = tmp;
            }
        }
    }
}
