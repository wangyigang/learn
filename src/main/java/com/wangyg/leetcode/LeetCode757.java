package com.wangyg.leetcode;


import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一个整数区间 [a, b]  ( a < b ) 代表着从 a 到 b 的所有连续整数，包括 a 和 b。
 * 给你一组整数区间intervals，请找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。
 * 输出这个最小集合S的大小。
 * 示例 1:
 * 输入: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]  //
 * 输出: 3
 * 解释:
 * 考虑集合 S = {2, 3, 4}. S与intervals中的四个区间都有至少2个相交的元素。
 * 且这是S最小的情况，故我们输出3。
 * 示例 2:
 * 输入: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
 * 输出: 5
 * 解释:
 * 最小的集合S = {1, 2, 3, 4, 5}.
 * 注意:
 * <p>
 * intervals 的长度范围为[1, 3000]。
 * intervals[i] 长度为 2，分别代表左、右边界。
 * intervals[i][j] 的值是 [0, 10^8]范围内的整数。
 */
public class LeetCode757 {

    @Test
    public void test1() {
        int[][] intervals = new int[][]{{1, 2}, {2, 3}, {2, 4}, {4, 5}};
        System.out.println(intersectionSizeTwo(intervals));
    }

    @Test
    public void test2() {
        int[][] intervals = new int[][]{{1, 3}, {1, 4}, {2, 5}, {3, 5}};
        System.out.println(intersectionSizeTwo(intervals));
    }

    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o2[0] - o1[0];
                }
                return o1[1] - o2[1];
            }
        });

        int[] rec = {-1, -1};
        int res = 0;
        for (int[] is : intervals) {
            if (is[0] <= rec[0]) {
                continue;
            } else if (is[0] <= rec[1]) {
                res++;
                rec[0] = rec[1];
            } else {
                rec[0] = is[1] - 1;
                res += 2;
            }
            rec[1] = is[1];
        }
        return res;
    }
}
