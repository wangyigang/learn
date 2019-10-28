package com.wangyg.leetcode;

import org.junit.Test;

import java.util.Stack;

/*
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:

输入: "()"
输出: true
示例 2:

输入: "()[]{}"
输出: true
示例 3:

输入: "(]"
输出: false
示例 4:

输入: "([)]"
输出: false
示例 5:

输入: "{[]}"
输出: true
 */
public class LeetCode20 {
    //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
    //使用栈进行解决问题
    @Test
    public void test(){
//        输入: "{[]}"
//        String string = "{[]}";
//        String string = "()[]{}";
        String string = "[])";

        Solution s = new Solution();
        System.out.println(s.isValid(string));
    }


    class Solution {
        public boolean isValid(String s) {
            if(s==null || "".equalsIgnoreCase(s.trim())){
                return true;
            }
            //
            Stack<Character> stack = new Stack<>();
            stack.push(s.charAt(0));
            //利用栈方式进行
            for(int i=1; i<s.length(); i++){
                //首先获取栈顶元素
                if(!stack.isEmpty()){
                    Character peek = stack.peek();
                    if((s.charAt(i) == ')' && peek=='(') ){
                        stack.pop();
                    }else if(s.charAt(i) == ']' && peek == '['){
                        stack.pop();
                    }else if(s.charAt(i) == '}' && peek == '{'){
                        stack.pop();
                    }else{
                        stack.push(s.charAt(i));
                    }
                }else{
                    stack.push(s.charAt(i));
                }

            }
            return stack.size() ==0 ? true: false;
        }
    }
}
