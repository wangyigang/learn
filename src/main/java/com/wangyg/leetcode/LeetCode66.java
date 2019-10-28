package com.wangyg.leetcode;

import org.junit.Test;
import sun.security.util.Length;

import java.util.Arrays;
import java.util.Stack;

public class LeetCode66 {
    @Test
    public void test() {
        int[] digits = new int[]{9};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.plusOne(digits)));
    }

    class Solution {

        public int[] plusOne(int[] digits) {
            if (digits == null)
                return null;

            //判断是否需要重新创建数组
            if (digits[digits.length - 1] == 9) {
                int cur = digits.length - 1;
                boolean flag = true;
                while (flag) {
                    digits[cur] = 0;
                    cur--;
                    if(cur == -1){
                        int[] result = new int[digits.length + 1];
                        for (int i = digits.length - 1; i >= 0; i--) {
                            result[i + 1] = digits[i];
                        }
                        result[0] =1;
                        return result;
                    }
                    if (digits[cur] == 9) {
                        flag = true;
                    } else {
                        flag = false;
                        digits[cur] += 1;
                    }
                }
            } else {
                digits[digits.length - 1] = digits[digits.length - 1] + 1;
            }

            return digits;
        }
    }
}
