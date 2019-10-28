package com.wangyg.leetcode;

import org.apache.hadoop.yarn.client.RMProxy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Leetcode118 {

    @Test
    public void test() {
        int num = 5;

        Solution solution = new Solution();
        for (List<Integer> list : solution.generate(5)) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }


    //使用动态规划方式解决- 杨辉三角问题
    class Solution {
        public List<List<Integer>> generate(int numRows) {

            List<List<Integer>> res = new ArrayList<>();

            if (numRows == 0) return res;

            List<Integer> res0 = new ArrayList<>();
            res0.add(1); //添加元素1
            res.add(res0);

            for (int i = 1; i < numRows; i++) {
                //创建数组
                List<Integer> tmp = new ArrayList<>();
                List<Integer> preRow = res.get(i - 1);

                tmp.add(1);

                for (int j = 1; j < i; j++) {  //j<i
                    tmp.add(preRow.get(j - 1) + preRow.get(j));
                }
                //最后一个位置加1
                tmp.add(1);
                //添加元素
                res.add(tmp);
            }
            return res;
        }
    }
}
