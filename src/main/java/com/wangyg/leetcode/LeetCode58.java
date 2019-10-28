package com.wangyg.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class LeetCode58 {

    @Test
    public void test() {
        String s = "Hello ";

        Solution solution = new Solution();
        System.out.println(solution.lengthOfLastWord(s));
    }


        class Solution {
        public int lengthOfLastWord(String s) {
            //优化1： 将前两部分进行整合
            if (s == null || s.length() == 0 || s.trim().length()==0) {
                return 0;
            }
            String S =s.trim();

            //优化2： 从后向前进行遍历
            StringBuffer sb = new StringBuffer();
            for (int i = S.length() - 1; i >= 0; i--){
                if (S.charAt(i) == ' '){
                    break;
                }
                sb.append(S.charAt(i));
            }
            return sb.toString().length();
        }
    }

}
