package com.wangyg.leetcode;

import org.junit.Test;

import java.util.Stack;

/*
给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

示例 1:

输入: "(()"
输出: 2
解释: 最长有效括号子串为 "()"
示例 2:

输入: ")()())"
输出: 4
解释: 最长有效括号子串为 "()()"
 */
public class LeetCode32 {
    @Test
    public void test() {
        String s1 = "(()"; //2
        String s2 = ")()())"; //4

        Solution solution = new Solution();
        System.out.println(solution.longestValidParentheses(s1));
        System.out.println(solution.longestValidParentheses(s2));

    }

    /*
        当我们遇到一个( 把他放在栈顶，遇到一个) 从栈中弹出，或者遍历完整个自子字符串后，
        栈中仍有元素，那么说明： 子字符串是无效的，，这种方法中，对每个
        偶数长度的子字符串都进行判断，并保存目前为止找到最长的有效子字符串的长度

     */
//    public class Solution {
//        public boolean isValid(String s) {
//            Stack<Character> stack = new Stack<Character>();
//            for (int i = 0; i < s.length(); i++) {
//                if (s.charAt(i) == '(') {
//                    stack.push('(');
//                } else if (!stack.empty() && stack.peek() == '(') {
//                    stack.pop();
//                } else { //为空，或者职位')'
//                    return false;
//                }
//            }
//            return stack.empty();
//        }
//        public int longestValidParentheses(String s) {
//            int maxlen = 0;
//            for (int i = 0; i < s.length(); i++) {
//                for (int j = i + 2; j <= s.length(); j+=2) {
//                    if (isValid(s.substring(i, j))) {
//                        maxlen = Math.max(maxlen, j - i);
//                    }
//                }
//            }
//            return maxlen;
//        }
//    }

    /**
     *  使用动态规划方式进行处理
     *  最终结果可以差分成单个步骤的结果
     */
    public class Solution {
        public int longestValidParentheses(String s) {
            int maxans = 0;  //最大的结果
            int dp[] = new int[s.length()]; //申请字符串长度大小的数组
            for (int i = 1; i < s.length(); i++) { //进行遍历
                if (s.charAt(i) == ')') {  //如果是)
                    if (s.charAt(i - 1) == '(') { //查看左边是否是(
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;  //动态规划，右边的和左边的永远有关
                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') { //
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    maxans = Math.max(maxans, dp[i]);
                }
            }
            return maxans;
        }
    }



}
