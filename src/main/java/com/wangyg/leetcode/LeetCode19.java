package com.wangyg.leetcode;

import org.junit.Test;

import java.util.List;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 */
public class LeetCode19 {
    @Test
    public void test() {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
        ListNode node = s.removeNthFromEnd(head, 2);

        while(node!= null){
            System.out.println(node.val);
            node= node.next;
        }
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //删除倒数的第n个节点，并返回头结点
    //思路： 双指针，先走n步，然后两个指针同时走，还需要一个指针，进行连接
    //为空/ 超过 / 正常 /头结点/ 非头结点
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null) return null;

            ListNode tmpHead = new ListNode(-1);
            tmpHead.next = head;
            ListNode cur = head;
            ListNode prev = tmpHead;
            while (n-- > 0) {
                cur = cur.next;
                if (cur == null ) {
                    //走到末尾   1->2
                    if( n>0){
                        return null;
                    }else if(n==0){
                          tmpHead.next = tmpHead.next.next;
                          return tmpHead.next;
                    }
                }
            }
            //如果删除的节点是头结点 /非头结点
            while (cur != null && prev != null) {
                prev = prev.next;
                cur = cur.next;
            }
            ListNode next = prev.next;
            if(next != null){
                prev.next = next.next;
            }else{
                prev.next = null;
            }
            return tmpHead.next;
        }

        public ListNode removeNthFromEnd2(ListNode head, int n) {
            ListNode dummy = new ListNode(0); //创建一个假头
            dummy.next = head;  //假头指向头结点
            ListNode first = dummy; //快指针first
            ListNode second = dummy; //慢指针
            // Advances first pointer so that the gap between first and second is n nodes apart
            for (int i = 1; i <= n + 1; i++) {
                first = first.next; //
            }
            //快慢指针同时进行移动
            while (first != null) {
                first = first.next;
                second = second.next;
            }
            //慢指针跳过这个要删除的节点
            second.next = second.next.next;
            return dummy.next;
        }
    }
}
