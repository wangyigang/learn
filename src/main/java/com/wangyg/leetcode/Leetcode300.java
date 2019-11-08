package com.wangyg.leetcode;

import org.junit.Test;
import util.Logger;

import java.util.Arrays;

public class Leetcode300 {
    @Test
    public void test() {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};

        Solution solution = new Solution();
        System.out.println(solution.lengthOfLIS(nums));
    }
    //自己实现一遍
    class Solution {
        public int lengthOfLIS(int[] nums) {
            //使用动态规划+二分查找
            int[] dp = new int[nums.length];
            int len =0;
            for (int num : nums) {
                //使用二分法进行查找
                int i = Arrays.binarySearch(dp, 0, len, num); //查询dp, 因为dp是有序排列的, 所以使用二分查找，查找dp
                Logger.info("i=" + i);
                if (i < 0) {
                    i = -(i + 1);
                }
                dp[i] = num;
                Logger.info("dp=" + Arrays.toString(dp));
                //这里是希望最小的值插入到第一个, 所以想要达到的效果就是，最小的放在前面，一次插入到合适的位置
                if ( i == len) {
                    len++;
                }
            }
            return len;
        }
    }


    //方法三
    //path是长度的定义

    /**
     *  min  i 一定是单调递增的，
     *
     *  所以使用动态规划+ 二分查找方式进行解决问题
     */
//    class Solution {
//        public int lengthOfLIS(int[] nums) {
//            //dp是动态规划中的数组
//            int[] dp = new int[nums.length]; //创建一个和原数组大小一样的数组
//            int len =0;
//            //进行遍历
//            for (int num : nums) {
//                int i = Arrays.binarySearch(dp, 0, len, num); //使用二分查找， 查找当前的num
//                if (i < 0) { //如果小于0 ，就表示当前没有
//                    i = -(i + 1);
//                    Logger.info(" i" +i );
//                }
//                //一直插入当前的最小值 ,从结果来看一致插入当前的最小值，然后如果当前列索引和Len 相同，就向后移动，继续进行插入
//                dp[i] = num;
//                Logger.info("dp="+ Arrays.toString(dp));
//                if (i == len) {
//                    len++;
//                    Logger.info(" len = " + len);
//                }
//            }
//            return len;
//        }
//    }


//    //方式二： 优化方式： 递归转为非递归
//
//    /**
//     * 转非递归思路：
//     * 将递归主过程进行拷贝
//     * 将相关递归函数相关的处理，全部转为缓存的数组进行处理
//     * 原先递归： 从后向前
//     * 非递归： 需要改为 从前向后进行处理
//     */
//    static class Solution {
//        public static int[] p = new int[10000];
//        public static int[] f = new int[10000];
//
//        public int lengthOfLIS(int[] nums) {
//            for (int i = 0; i < f.length; i++) {
//                f[i] =0;
//            }
//            for (int i = 0; i < nums.length; ++i) {
//                p[i] = nums[i];
//            }
//            int n = nums.length;
//            p[n] = 10000000;
//            ++n;
//
//            for (int idx = 0; idx < n; ++idx) {
//                int ans = 0;
//                for (int i = 0; i < idx; ++i) {
//                    if (p[idx] > p[i]) {
//                        ans = Math.max(ans, f[i]); //将结果存入f 缓存数组中
//                    }
//                }
//                f[idx] = ans+1;
//            }
//            return f[n-1] -1;
//        }
//    }


//    /**
//     * 动态规划：
//     * 套路： 先 暴力搜索  -》 去冗余 -》 改为非递归
//     * <p>
//     * 上升子序列中，可以跳过，不一定要 元素紧挨着，所以，可以使用动态规划进行求解
//     * LIS: 最长上升子序列
//     * 套路： 暴力求解：  使用搜索算法， 递归方式进行处理
//     * 可以从前向后搜， 也可以从后向前搜索
//     */
//    public static class Solution {
//        //方法一： 暴力求解
//        public static int[] caled = new int[10000]; //保存以前计算的数据
//        public static int[] p = new int[10000];
//
//
//        public static int robot(int idx, int[] nums) { //传入的值： idx: 一个索引，最后一个元素的位置，从后向前进行查找, nums, 和原数组差不多
//            if (idx < 0) {
//                return 0;
//            }
//            //如果已经计算过了， 就不再使用递归进行重复计算了，节省效率
//            if (caled[idx] > 0) { //caled保存已经计算过的数据，不必进行重复计算
//                return caled[idx];
//            }
//            int ans = 0;
//            for (int i = 0; i < idx; ++i) {
//                if (nums[idx] > nums[i]) { //从后向前找，后面元素 比前面元素大，才可以
//                    ans = Math.max(ans, robot(i, nums)); //进行递归查找， 进行比较结果,获取较大的
//                    //从前向后进行递归计算，比较两者这件的关系
//                }
//            }
//            caled[idx] = ans + 1;
//            return ans + 1;
//        }
//
//        public int lengthOfLIS(int[] nums) {
//            //进行初始化操作
//            for (int i = 0; i < caled.length; ++i) {
//                caled[i] = 0;
//            }
//            //进行初始化操作
//            for (int i = 0; i < nums.length; ++i) {
//                p[i] = nums[i]; //进行赋值
//            }
//            // p 和nums中元素相同，只不过 p在数组最后增加一个最大值，方便计算，
//            int n = nums.length;
//            p[n] = 1000000;
//            ++n;
//            return robot(n - 1, p) - 1;
//        }
//    }
}
