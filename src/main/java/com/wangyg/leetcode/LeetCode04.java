package com.wangyg.leetcode;

import org.junit.Test;

public class LeetCode04 {
    @Test
    public void test() {
        Solution s = new Solution();
        int[] num1 = new int[]{1, 2};
        int[] num2 = new int[]{3, 4};
        System.out.println(s.findMedianSortedArrays(num1, num2));
    }

    class Solution {
        public double findMedianSortedArrays(int[] A, int[] B) {
            int m = A.length; //A的长度
            int n = B.length; //B的长度
            if (m > n) { // m指向小的数组， n 指向大的数组
                int[] temp = A;
                A = B;
                B = temp; //数组内容交换
                int tmp = m;
                m = n;
                n = tmp;  //数组长度进行交换
            }
            //始终 m指向小的数组，n指向大的数组
            int iMin = 0;
            int iMax = m;  //
            int halfLen = (m + n + 1) / 2;   //m+n+1/2指向中间值
            while (iMin <= iMax) {
                int i = (iMin + iMax) / 2;
                int j = halfLen - i;  //最后一个
                if (i < iMax && B[j - 1] > A[i]) {
                    iMin = i + 1; // i is too small
                } else if (i > iMin && A[i - 1] > B[j]) {
                    iMax = i - 1; // i is too big
                } else { // i is perfect
                    int maxLeft = 0;
                    if (i == 0) {
                        maxLeft = B[j - 1];
                    } else if (j == 0) {
                        maxLeft = A[i - 1];
                    } else {
                        maxLeft = Math.max(A[i - 1], B[j - 1]);
                    }
                    if ((m + n) % 2 == 1) {
                        return maxLeft;
                    }

                    int minRight = 0;
                    if (i == m) {
                        minRight = B[j];
                    } else if (j == n) {
                        minRight = A[i];
                    } else {
                        minRight = Math.min(B[j], A[i]);
                    }
                    return (maxLeft + minRight) / 2.0;
                }
            }
            return 0.0;
        }
    }
}
