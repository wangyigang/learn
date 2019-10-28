package com.wangyg.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

例如，给出 n = 3，生成结果为：

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

 */
public class LeetCode22 {
    @Test
    public void test(){
        Solution s = new Solution();
        for (String s1 : s.generateParenthesis(3)) {
            System.out.println(s1);
        }
    }
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            //递归调用方法
            backtrack(res,"",0,0, n);

            return res;
        }

        private void backtrack(List<String> res,String s, int left, int right, int max) {
            if(s.length() == max*2){
                res.add(s);
                return;
            }
            if(left< max){
                backtrack(res, s+"(", left+1, right, max);
            }
            if(right< left){
                backtrack(res, s+")",left , right+1, max);
            }
        }
    }

}
