package com.wangyg.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

public class Leetcode125 {
    @Test
    public void test1(){
        String s = "A man, a plan, a canal: Panama";
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(s));
    }
    @Test
    public void test2(){
        String  s= "A man, a plan, a canal: Panama";
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(s));
    }

    class Solution {
        public boolean isPalindrome(String s) {
            if(s== null) return false;
            //使用正则表达式处理，字母数字
            s = s.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
            //使用双指针进行判断
            int left = 0;
            int right =s.length()-1;

            while (left < right) {
                char leftChar= s.charAt(left);
                char rightChar = s.charAt(right);
                if(leftChar != rightChar){
                    return false;
                }else{
                    left++;
                    right--;
                }

            }
            return true;
        }
    }
}
