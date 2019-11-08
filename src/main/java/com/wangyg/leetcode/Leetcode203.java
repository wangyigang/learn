package com.wangyg.leetcode;

import org.junit.Test;

/**
 * 删除链表中等于给定值val的所有节点
 */
public class Leetcode203 {
    @Test
    public void test203() {
        //头部， 中间，尾部

        int val =6;
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(6);

        node1.next = node2;
        node2.next= node3;
        node3.next = node4;
        node4.next = node5;
        node5.next= node6;
        node6.next = node7;

        Solution solution  = new Solution();
        ListNode res = solution.removeElements(node1, val);
        while (res!= null){
            System.out.println(res.val);
            res = res.next;
        }
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode removeElements(ListNode head, int val) {
            ListNode tmpHead = new ListNode(-1);
            tmpHead.next = head;
            //分情况讨论
            ListNode prev = tmpHead;
            ListNode cur = head;

            //进行遍历
            while (cur != null) {
                if(cur.val == val) {
                    while (cur != null &&cur.val == val) { //最后的情况
                        cur = cur.next;
                    }
                    prev.next= cur;
                }else{ //不相同，同时向右移动
                    cur = cur.next;
                    prev= prev.next;
                }
            }
            return tmpHead.next;
        }
    }
}
