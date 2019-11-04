package com.wangyg.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 * <p>
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode198 {

    @Test
    public void test1() {
        int[] nums = new int[]{1, 2, 3, 1};
        Solution solution = new Solution();
        System.out.println(solution.rob(nums));
    }

    @Test
    public void test2() {
        int[] nums = new int[]{2, 7, 9, 3, 1};
        Solution solution = new Solution();
        System.out.println(solution.rob(nums));
    }

    /**
     * 动态转移方程: 数学公式
     */
    class Solution {
        public int rob(int[] nums) {
            if(nums.length ==0) return 0;
            int[] tmp = new int[nums.length + 1]; //因为涉及到前一个，所以数组中第一个的前一个最好也多开辟一个空间，比较合适，所以，默认设置为0
            //动态规划公式;
            tmp[0]=0;
            tmp[1] = nums[0];
            //创建动态规划数组
            for (int i = 2; i <= nums.length; i++) {
                tmp[i] = Math.max(tmp[i-1], tmp[i-2]+nums[i-1]); //nums[i-1]表示当前值
            }
            System.out.println(Arrays.toString(tmp));
            return tmp[nums.length];
        }
        public int rob1(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length + 1];
            dp[0]=0;
            dp[1] = nums[0];
            //dp[i] = max(dp[i-2]+nums[i], dp[i-1])
            for (int i = 2; i <= nums.length; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
            }
            return dp[nums.length];
        }
    }


}
