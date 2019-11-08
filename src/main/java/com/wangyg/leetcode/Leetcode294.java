package com.wangyg.leetcode;

import org.junit.Test;

public class Leetcode294 {
    @Test
    public void test(){
        int n = 10;
        Solution solution = new Solution();
        System.out.println(solution.countPrimes(n));
    }

    class Solution {
        public int countPrimes(int n) {
            int cnt =0;
            cnt = countPrime(n);
            return cnt;
        }

        private int countPrime(int n) {
            int count =0;
            for (int i = 2; i < n; i++) {
                if(isPrime(i)){
                    count++;
                }
            }
            return count;
        }

        private boolean isPrime(int n) {
            int sqrt = (int)Math.sqrt(n);
            for (int i = 2; i <= sqrt; i++) {
                if(n % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
