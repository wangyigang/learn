package com.wangyg.leetcode;

import org.junit.Test;

public class LeetCode38 {
    @Test
    public void test(){
        Solution solution = new Solution();
        System.out.println(solution.countAndSay(5));
    }

    /*
        每次王字符串中插入的有数字target的个数和数字target，所以我们要一次遍历字符串，
        统计相邻的数字相邻的个数，然后一旦相邻的数字不同了，我们就需要把统计到的个数count
        和数字target插入字符串，遍历完了要注意最后一个count和target是没有插入的，
        需要在外部进行插入，最后再把缓存的字符串复制到以前的字符串中，
     */
    class Solution {
        public String countAndSay(int n) {
            String str = "1"; //初始值1
            for (int i = 2; i <= n; i++) { //从2开始，一直到n
                StringBuilder builder = new StringBuilder(); //创建stringBuilder
                char pre = str.charAt(0); //
                int count = 1;
                for (int j = 1; j < str.length(); j++) {
                    char c = str.charAt(j);
                    if (c == pre) { //如果相同，count++
                        count++;
                    } else {  //相邻元素不相同， 需要将统计的个数和元素插入到结果中
                        builder.append(count).append(pre);
                        pre = c;
                        count = 1;
                    }
                }
                builder.append(count).append(pre);
                str = builder.toString();
            }

            return str;
        }
    }
}
