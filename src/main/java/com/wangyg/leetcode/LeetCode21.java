package com.wangyg.leetcode;

import org.junit.Test;
import org.scalatest.enablers.Definition;

public class LeetCode21 {

    @Test
    public void test() {
        /*
        输入：1->2->4, 1->3->4
        输出：1->1->2->3->4->4
         */
        ListNode lHead = new ListNode(1);
        ListNode lnode2 = new ListNode(2);
        ListNode lnode3 = new ListNode(4);
        lHead.next = lnode2;
        lnode2.next = lnode3;
        ListNode rHead = new ListNode(1);
        ListNode rnod2 = new ListNode(3);
        ListNode rnode3 = new ListNode(4);
        rHead.next= rnod2;
        rnod2.next= rnode3;

        Solution s = new Solution();
        ListNode mergeTwoLists = s.mergeTwoLists(lHead, rHead);

        while (mergeTwoLists!= null){
            System.out.println(mergeTwoLists.val);
            mergeTwoLists = mergeTwoLists.next;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //合并两个有序链表
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if(l1 == null) return l2;
            if(l2==null) return l1;
            ListNode newHead = null;
            if(l1.val < l2.val){
                newHead = l1;
                l1 = l1.next;
            }else{
                newHead= l2;
                l2= l2.next;
            }
            ListNode tail = newHead; //创建一个尾指针，指向头结点

            while(l1!= null && l2!= null){
                if(l1.val  < l2.val){
                    tail.next = l1;
                    l1 = l1.next;
                    tail = tail.next;
                }else{
                    tail.next = l2;
                    l2 = l2.next;
                    tail = tail.next;
                }
            }
            //判断是否还有剩余
            if(l1 !=null){
                tail.next = l1;
            }
            if(l2!=null){
                tail.next = l2;
            }


            return newHead;
        }
    }
}
