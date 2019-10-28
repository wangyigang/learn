package com.wangyg.leetcode;

import org.junit.Test;
import org.scalatest.enablers.Definition;

public class Leetcode141 {
    @Test
    public void test(){

    }

   class ListNode {
       int val;
       ListNode next;
       ListNode(int x) {
           val = x;
           next = null;
       }
   }
    public class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null) return false;

            ListNode first = head;
            ListNode second = head.next;
            if(second == null) return false;
            while (first != null && second != null) {
                if (first == second) {
                    return true;
                }else{
                    ListNode next = second.next;
                    if (next == null) {
                        return false;
                    }
                    second= next.next;
                    first = first.next;
                }
            }
            return false;
        }
    }
}
