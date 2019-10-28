package com.wangyg.leetcode;

import org.junit.Test;

/*
给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

    k 是一个正整数，它的值小于或等于链表的长度。

    如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

    示例 :

    给定这个链表：1->2->3->4->5

    当 k = 2 时，应当返回: 2->1->4->3->5

    当 k = 3 时，应当返回: 3->2->1->4->5

    说明 :

    你的算法只能使用常数的额外空间。
    你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class LeetCode25 {
    @Test
    public void test() {
        //1->2->3->4->5
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next=node3;
        node3.next=node4;
        node4.next= node5;
        Solution s = new Solution();
        ListNode listNode = s.reverseKGroup(head, 3);
        while(listNode!= null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //指针移动，进行处理
    /*
            dummy 1    2   3   4   5
                 head1  tail
     */
    class Solution{
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode prev = dummy;
            ListNode tail = dummy;

            while (true){
                int count =0;
                while (tail != null && count!= k){
                    count++;
                    tail = tail.next;
                }
                if(tail == null) break;
                //首先记录
                ListNode tmpHead= prev.next;
                while (prev.next != tail){
                    //进行尾插法
                    ListNode cur = prev.next;
                    prev.next = cur.next; //切断前面的关系
                    //插入后面
                    cur.next = tail.next;
                    tail.next = cur; //改变后面的连接关系
                }
                prev= tmpHead;
                tail = tmpHead;

            }
            return dummy.next;
        }
    }
}
