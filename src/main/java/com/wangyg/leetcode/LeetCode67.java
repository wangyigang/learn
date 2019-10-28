package com.wangyg.leetcode;

import org.junit.Test;

public class LeetCode67 {
    @Test
    public void test() {
        String a = "1", b = "111";
        Solution solution = new Solution();
        System.out.println(solution.addBinary(a, b));
    }

    class Solution {
        public String addBinary(String a, String b) {
            if (a == null || a.trim().isEmpty())
                return b;
            if (b == null || b.trim().isEmpty())
                return a;

            StringBuffer sb = new StringBuffer();
            int carry = 0;
            int i = a.length() - 1;
            int j = b.length() - 1;
            for (; i >= 0 && j >= 0; i--, j--) {
                int left = a.charAt(i) - '0';
                int right = b.charAt(j) - '0';
                Character c = (char) ((left + right + carry) % 2 + '0');
                carry = (left + right+carry) / 2;
                sb.insert(0, c);
            }
            //剩余部分
            if (i >= 0) {
                for (; i >= 0; i--) {
                    int left = a.charAt(i) - '0';
                    char c = (char) ((left + carry) % 2 + '0');
                    carry = (left+carry)/2;
                    sb.insert(0,c );
                }
            }
            if (j >= 0) {
                for (; j >= 0; j--) {
                    int left = b.charAt(j) - '0';
                    char c = (char) ((left + carry) % 2 + '0');
                    carry = (left+carry)/2;
                    sb.insert(0,c );
                }
            }
            if (carry == 1) {
                sb.insert(0, carry);
            }
            return sb.toString();

        }
    }
}
