package com.wangyg.leetcode;

import org.junit.Test;

public class Leetcode1232 {
    @Test
    public void test(){
        int[][] coordinates = new int[][]{{1, 1}, {2, 2}, {3, 4}, {4, 5}, {5, 6}, {7, 7}};

        Solution solution = new Solution();
        System.out.println(solution.checkStraightLine(coordinates));
    }

    class Solution {
        public boolean checkStraightLine(int[][] coordinates) {
            if (coordinates == null || coordinates.length<=1) {
                return true;
            }
            int x1 = coordinates[0][0];
            int y1 = coordinates[0][1];

            int x2 = coordinates[1][0];
            int y2 = coordinates[1][1];

//            double k = (y2-y1)/ (x2-x1);

            //思维太死了 ，感觉自己

            for (int i = 2; i < coordinates.length; i++) {
                int x = coordinates[i][0];
                int y = coordinates[i][1];
               if ((y-y1)* (x-x2) != (y-y2)*(x-x1)) return false;
            }
            return true;
        }
    }
}
