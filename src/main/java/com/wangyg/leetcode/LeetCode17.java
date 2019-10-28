package com.wangyg.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LeetCode17 {
    @Test
    public void test() {
        Solution s = new Solution();
        System.out.println(s.letterCombinations("23").toString());
    }

    class Solution {
        public List<String> letterCombinations(String digits) {
            List<String> list = new ArrayList<>();
            String[] s = new String[digits.length()];
            if (s.length == 0) {
                return list;
            }
            for (int i = 0; i < digits.length(); i++) {
                switch (digits.charAt(i)) {
                    case '2':
                        s[i] = "abc";
                        break;
                    case '3':
                        s[i] = "def";
                        break;
                    case '4':
                        s[i] = "ghi";
                        break;
                    case '5':
                        s[i] = "jkl";
                        break;
                    case '6':
                        s[i] = "mno";
                        break;
                    case '7':
                        s[i] = "pqrs";
                        break;
                    case '8':
                        s[i] = "tuv";
                        break;
                    case '9':
                        s[i] = "wxyz";
                        break;
                }
            }
            getAllTheStrings(s, 0, list, "");
            return list;
        }
        //获取所有的字符串
        private void getAllStrings(String[] str , int index, List<String> list, String tmp){
            if(index == str.length -1){
                for(int i=0; i<str[index].length(); i++){
                    list.add(tmp+str[index].charAt(i));
                }
            }
        }

        private void getAllTheStrings(String[] str, int index, List<String> list, String temp) {
            if (index == str.length - 1) { //这里是递归到最后一层时的逻辑
                for (int i = 0; i < str[index].length(); i++) {
                    list.add(temp + str[index].charAt(i));
                }
            } else { //这里是递归的逻辑
                for (int i = 0; i < str[index].length(); i++) {  //循环+递归
                    getAllTheStrings(str, index + 1, list, temp + str[index].charAt(i));
                }
            }
        }
    }
}
