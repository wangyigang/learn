package com.wangyg.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
示例 1：
输入：
  s = "barfoothefoobarman",
  words = ["foo","bar"]
输出：[0,9]
解释：
从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
输出的顺序不重要, [9,0] 也是有效答案。
示例 2：

输入：
s = "wordgoodgoodgoodbestword",
words = ["word","good","best","word"]
输出：[]
 */
public class LeetCode30 {
    @Test
    public void test() {
        String s = "barfoothefoobarman";
        String[] words = new String[]{"foo", "bar"};

        Solution solution = new Solution();
        for (Integer integer : solution.findSubstring(s, words)) {
            System.out.println(integer);
        }
    }

    /*
        思路：  滑动窗口思想
            判断每个字符串是否相同，只要相同，把相同的保存即可，然后返回
     */
    class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> res = new ArrayList<>();

            if (s == null || s.length() == 0 || words == null || words.length == 0)
                return res;

            HashMap<String, Integer> map = new HashMap<>();
            //获取单个字符的长度
            int one_word = words[0].length();
            //获取字符数组的长度
            int word_num = words.length;

            int all_len = one_word * word_num;
            for (String word : words) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
            for (int i = 0; i < s.length() - all_len + 1; i++) { //遍历每个单词
                String tmp = s.substring(i, i + all_len); //获取每个单词
                HashMap<String, Integer> tmp_map = new HashMap<>();
                for (int j = 0; j < all_len; j += one_word) { //再遍历当前截取到的tmp的值，进行截取
                    String w = tmp.substring(j, j + one_word); //判断当前获取的单词
                    tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                }
                //map.equals()方法 判断map 中的每个元素是否都相同
                if (map.equals(tmp_map)) res.add(i);
            }
            return res;
        }
    }
}
