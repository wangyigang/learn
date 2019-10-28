package com.wangyg.leetcode;

import org.junit.Test;
import util.Logger;

public class Leetcode168 {
    @Test
    public void test() {
        int n = 28;
        Solution solution = new Solution();
        System.out.println(solution.convertToTitle(n));
    }


    class Solution {

        public String convertToTitle(int n) {
            if (n <= 0)
                return "";
            StringBuilder sb = new StringBuilder();
            while (n > 0) {
                n--;
                char c = (char) (n%26+'A');
                System.out.println(c);
                sb.append(c);
                //迭代改变条件
                n = n / 26;
            }

            return  sb.reverse().toString();
        }


        public String convertToTitle1(int n) {
            if (n <= 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            while (n > 0) {
                n--;
                char c = (char) (n % 26 + 'A');
//                Logger.debug(c);
                sb.append(c);
                n = n / 26;
            }
            return sb.reverse().toString();
        }
    }
}
