package com.wangyg.leetcode;

import org.junit.Test;

public class LCP1 {
    @Test
    public void test(){

    }

    class Solution {
        public int game(int[] guess, int[] answer) {
            int count=0;
            for (int i = 0; i < guess.length; i++) {
                if (guess[i] == answer[i]) {
                    count++;
                }
            }
            return count;
        }
    }
}
