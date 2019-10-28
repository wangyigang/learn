package com.wangyg.leetcode;

import org.junit.Test;
import util.Logger;

public class Leetcode171 {
    @Test
    public void test() {
        String s = "AB";

        Solution solution = new Solution();
        System.out.println(solution.titleToNumber(s));
    }


    //还是使用数学思维，进行找数学规律
    //26进制转10进制
    class Solution {
        public int titleToNumber(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int res =0;
            for (int i = 0; i < s.length(); i++) {
                int num = s.charAt(i)-'A' +1;
                res = res*26+ num;
            }
            return res;
        }

        public int titleToNumber1(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int ans=0;
            for(int i=0;i<s.length();i++) {
                int num = s.charAt(i) - 'A' + 1;
                ans = ans * 26 + num;
            }

            return ans;
        }
    }
}
