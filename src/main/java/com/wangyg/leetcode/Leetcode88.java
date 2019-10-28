package com.wangyg.leetcode;

import org.junit.Test;

import java.util.Arrays;

//合并两个有序的数组
public class Leetcode88 {
    @Test
    public void test() {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int m = 3;

        int[] nums2 = new int[]{2, 5, 6};
        int n = 3;

        Solution solution = new Solution();
        solution.merge(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));
    }

    class Solution {
        //方式一
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            //参数含义：原数组， 起始位置，   目标数组，目标数组的起始位置， 要copy的长度
            //将num2从0位置开始插入到nums1中的m 后面，插入n个元素
            System.arraycopy(nums2, 0, nums1, m, n);
            Arrays.sort(nums1);
        }

        //方式二：
        //使用指针的方式进行移动指针位置，进行插入元素
        public void merge2(int[] nums1, int m, int[] nums2, int n) {
            int len1 = m - 1;
            int len2 = n - 1;
            int len = m + n - 1;
            while(len1 >= 0 && len2 >= 0) {
                // 注意--符号在后面，表示先进行计算再减1，这种缩写缩短了代码
                nums1[len--] = nums1[len1] > nums2[len2] ? nums1[len1--] : nums2[len2--];
            }
            //将nums2剩余的还没有进行比较的，直接插入到num1的前面位置
            // 表示将nums2数组从下标0位置开始，拷贝到nums1数组中，从下标0位置开始，长度为len2+1
             System.arraycopy(nums2, 0, nums1, 0, len2 + 1);
        }

    }
}
