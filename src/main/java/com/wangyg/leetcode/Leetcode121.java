package com.wangyg.leetcode;

import org.junit.Test;


public class Leetcode121 {
    @Test
    public void test() {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        Solution solution = new Solution();
        System.out.println(solution.maxProfit(prices));
    }

    //买股票的最佳时机
    //动态规划转台方程=> res= max(res, prices[i]-min_val);
    //数组是每天股票的价格，要获取最大的利益
    class Solution {
        public int maxProfit(int[] prices) {
            int res = 0;
            int min_val = Integer.MAX_VALUE;//integer当前最大值
            for (int i = 0; i < prices.length; i++) {
                min_val = Math.min(min_val, prices[i]);
                //当前索引值和 min_val 进行比较，获取最小值，然后res,和prices[i]
                //收益最大= 最大的res, 和每天价格-当前最小值，进行比较，获取最大值，这样就获取利润最大值
                //最大利益：所以是Max, 每天价格最大值和最小值的差值，就是利益
                res = Math.max(res, prices[i] - min_val);
            }
            return res;
        }
    }
}
