package com.wangyg.leetcode;

import org.junit.Test;
import sun.rmi.server.InactiveGroupException;

import java.util.HashMap;
import java.util.Map;

/*
给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

说明:

返回的下标值（index1 和 index2）不是从零开始的。
你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
示例:

输入: numbers = [2, 7, 11, 15], target = 9
输出: [1,2]
解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2
*/

public class Leetcode167 {
    @Test
    public void test() {
        int[] numbers = new int[]{2, 7, 11, 15};
        int target = 9;
        Solution solution = new Solution();
        for (int num : solution.twoSum(numbers, target)) {
            System.out.println(num);
        }
    }

    class Solution {
        public int[] twoSum1(int[] numbers, int target) {

            int[] res = new int[]{0, 0};
            for (int i = 0; i < numbers.length - 1; i++) {
                for (int j = i + 1; j < numbers.length; j++) {
                    if (numbers[i] + numbers[j] == target) {
                        res[0] = i + 1;
                        res[1] = j + 1;
                        return res;
                    }
                }
            }
            return res;
        }

        public int[] twoSum2(int[] numbers, int target) {
            int[] res = new int[]{0, 0};
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < numbers.length; i++) {
                int cur = numbers[i];
                int residue = target - cur;
                if (map.containsKey(cur)) {
                    res[1] = i + 1; //当前下标
                    res[0] = map.get(cur);
                } else {
                    //put 下标+1
                    //两个下标
                    map.put(residue, i + 1);
                }
            }
            return res;
        }

        //二分法， 最左边和最右边
        public int[] twoSum(int[] numbers, int target) {
            int[] res = new int[2];
            int low = 0;
            int high = numbers.length - 1;
            while (low < high) {
                if (numbers[low] + numbers[high] > target) //加和大了，右边向左移动
                    high--;
                else if (numbers[low] + numbers[high] < target)// 加和效率，左边向右移动
                    low++;
                else { //如果加和正好
                    res[0] = low + 1;
                    res[1] = high + 1;
                    return res;  //进行设置值，然后
                }
            }
            return res;
        }
    }
}
