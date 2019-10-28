package com.wangyg.leetcode;

import org.junit.Test;

public class LeetCode3 {
    @Test
    public void test() {
        Solution solution = new Solution();
        //输入: 1->1->2->3->3
        //输出: 1->2->3
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        while (solution.deleteDuplicates(head) != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    //删除重复元素
    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if(head == null|| head.next == null){
                return head;
            }

            ListNode cur = head;
            ListNode next = head.next;
            //1->1 ->1 ->2->3->3
            while (next != null) {
                if (cur.val == next.val) {
                    while (next!= null &&  cur.val == next.val) {
                        next = next.next;
                    }
                    //进行改变
                    cur.next = next;
                }else{
                    cur = next;
                    next= next.next;
                }

            }
            return head;
        }
    }
}
