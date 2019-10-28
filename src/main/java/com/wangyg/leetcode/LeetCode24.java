package com.wangyg.leetcode;

import org.junit.Test;

public class LeetCode24 {
    @Test
    public void test(){
        ListNode head=  new ListNode(1);
        ListNode node2=  new ListNode(2);
        ListNode node3=  new ListNode(3);
//        ListNode node4=  new ListNode(4);
        head.next = node2;
        node2.next = node3;
//        node3.next = node4;

        Solution s  = new Solution();
        ListNode listNode = s.swapPairs(head);
        while(listNode!= null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }


    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    //进行两两交换
    class Solution {
        public ListNode swapPairs(ListNode head) {
            if(head == null) return null;
            if(head.next == null) return head;

            //双指针  /一个节点/双节点/ 奇数节点
            ListNode cur= head.next;
            ListNode prev = head;
            while(cur != null && prev != null){
                //交换节点值
                int tmp = cur.val;
                cur.val = prev.val;
                prev.val = tmp;

                //指针后移--空指针问题
                if(prev.next !=null){
                    prev= prev.next.next;
                }else{
                    prev=null;
                }
                if(cur.next == null){
                    cur = null;
                }else{
                    cur = cur.next.next;
                }
            }

            return head;
        }
    }
}
