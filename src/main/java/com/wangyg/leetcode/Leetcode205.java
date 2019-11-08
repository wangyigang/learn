package com.wangyg.leetcode;

import org.junit.Test;
import util.Logger;

import java.util.HashMap;
import java.util.Map;

/*
给定两个字符串 s 和 t，判断它们是否是同构的。

如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。

所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。

示例 1:

输入: s = "egg", t = "add"
输出: true
示例 2:

输入: s = "foo", t = "bar"
输出: false
示例 3:

输入: s = "paper", t = "title"
输出: true

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/isomorphic-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode205 {
    @Test
    public void test() {
        String s = "egg";
        String t = "add";
        Solution solution = new Solution();
        System.out.println(solution.isIsomorphic(s, t));
    }


    /**
     * 判断两个字符串字符出现的位置是否相同
     * 同一个字符在两个字符串中出现的索引位置时相同的
     */
    class Solution {
        /**
         * 判断是否是同构字符串
         *
         * @param s
         * @param t
         * @return
         */
        public boolean isIsomorphic(String s, String t) {

            Map<Character, Integer> map_1 = new HashMap<Character, Integer>();
            Map<Character, Integer> map_2 = new HashMap<Character, Integer>();

            if (s.length() != t.length())
                return false;

            /**
             * 两个同时不存在 ： 同时插入 将key 对应的索引位置 插入, <key 是对应字符， value是对应的索引>
             * 两个同事存在：  进行判断:  判断对应字符串 的索引是否相同，如果相同，就返回true
             * 否则  返回false
             *
             */
            for (int i = 0; i < s.length(); i++) {
                //判断是否存在key 如果两个都不存在就全部放进去

                if ((!map_1.containsKey(s.charAt(i))) && (!map_2.containsKey(t.charAt(i)))) {
                    map_1.put(s.charAt(i), i);
                    map_2.put(t.charAt(i), i);
                } else if (map_1.containsKey(s.charAt(i)) && map_2.containsKey(t.charAt(i))) {
                    if (map_1.get(s.charAt(i)) == map_2.get(t.charAt(i)))
                        continue;
                    else
                        return false;
                } else
                    return false;
            }
            return true;
        }

        public boolean isIsomorphic1(String s, String t) {
            char[] ch1 = s.toCharArray();
            char[] ch2 = t.toCharArray();
            int len = s.length();
            for (int i = 0; i < len; i++) {
                int of = s.indexOf(ch1[i]);
                int index2 = t.indexOf(ch2[i]);
                Logger.info("of=" + of + " index2=" + index2);
                if (of != index2) {
                    return false;
                }
            }
            return true;
        }
    }
}
