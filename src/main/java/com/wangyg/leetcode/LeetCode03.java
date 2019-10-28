package com.wangyg.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;

public class LeetCode03 {
    @Test
    public void test() {
        String s = "pwwkew";
        Solution03 sl = new Solution03();
        System.out.println(sl.lengthOfLongestSubstring3(s));
    }
}

class Solution03 {
    //最长子序列
    //缺陷：复杂度太高,时间复杂度为O(n^3), 但其实有很多重复无用的判断，可以进行优化
    public int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s.trim())) {
            return 0;
        }
        int lenght = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (isDuplicate(s, i, j)) {
                    lenght = lenght < j - i + 1 ? j - i + 1 : lenght;
                }
            }
        }
        return lenght;
    }

    private boolean isDuplicate(String s, int left, int right) {
        //使用set进行去重
        Set<Character> set = new HashSet<>();
        for (int i = left; i <= right; i++) {
            if (set.contains(s.charAt(i))) {
                return false;
            } else {
                set.add(s.charAt(i));
            }
        }
        return true;
    }

    //无重复子串的最大长度
    public int lengthOfLongestSubstring2(String s) {
        int maxLength = 0;
        char[] chars = s.toCharArray(); //转为数组
        int leftIndex = 0;//左侧索引
        for (int i = 0; i < chars.length; i++) {
            for (int index = leftIndex; index < i; index++) {
                if (chars[index] == chars[i]) {
                    maxLength = Math.max(maxLength, i - leftIndex);
                    leftIndex = index + 1;
                    break;
                }
            }
        }
        return Math.max(chars.length - leftIndex, maxLength);
    }

    /*
        思路：
       暴力方法中，反复检查一个子字符串是否含有重复的字符，但这是没有必要的，
       如果从索引 i 到 索引j-1之间的 子字符串 sij-1 已经没有重复的元素,
       我们只需要检查s[j] 对应的字符是否已经存在于子字符串sij 中

       检查一个字符是否存在于一个子字符串中，可以检查整个子字符串，这将产生一个
       复杂度为O(n^2)的算法，但我们可以做得更好

       通过使用hashset作为滑动窗口，可以用O(1)的时间来完成对字符是否在当前的
       子字符串中的检查

        滑动窗口是数组/子字符串问题中常用的抽象概念, 窗口通常是在数组/字符串中
        由开始和结束索引 定义的一系列元素的集合，即 [i, j)[i,j)
        （左闭，右开）
        而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。
        例如，我们将 [i, j)[i,j) 向右滑动 11 个元素，则它将变为 [i+1, j+1)[i+1,j+1)
        （左闭，右开）。

        回到我们的问题，我们使用 HashSet 将字符存储在当前窗口 [i, j)[i,j)
        （最初 j = ij=i）中。 然后我们向右侧滑动索引 jj，如果它不在 HashSet 中，
        我们会继续滑动 jj。直到 s[j] 已经存在于 HashSet 中。
        此时，我们找到的没有重复字符的最长子字符串将会以索引 ii 开头。
        如果我们对所有的 ii 这样做，就可以得到答案。
       */
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>(); //使用hashset定义一个滑动窗口
        int ans = 0, i = 0, j = 0; // i j
        while (i < n && j < n) { //滑动窗口限制, n为字符串的长度
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) { //如果不包含右侧元素，
                set.add(s.charAt(j++)); //则添加到滑动窗口中
                ans = Math.max(ans, j - i); //ans = ans 和j-i最大值
            } else { //否则删除左侧i 位置元素，然后向后移动一个位置
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

}