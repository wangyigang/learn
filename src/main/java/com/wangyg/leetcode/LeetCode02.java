package com.wangyg.leetcode;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.junit.Test;

import java.util.List;

public class LeetCode02 {
    @Test
    public void test() {
        Solution s = new Solution();
        /*
        输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
        输出：7 -> 0 -> 8
        原因：342 + 465 = 807
         */
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        l1.next = l2;
        ListNode l3 = new ListNode(3);
        l2.next = l3;

        ListNode r1 = new ListNode(5);
        ListNode r2 = new ListNode(6);
        ListNode r3 = new ListNode(4);
        r1.next = r2;
        r2.next = r3;
        ListNode listNode = s.addTwoNumbers(l1, r1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);//创建一个假头
        ListNode left = l1;
        ListNode right = l2;
        ListNode cur = dummyHead;
        //进位
        int carry = 0; //默认为0
        while (left != null || right != null) {
            int leftNum = (left != null) ? left.val : 0;
            int rightNum = (right != null) ? right.val : 0;
            int sum = carry + leftNum + rightNum;
            carry = sum / 10; //获取进位
            cur.next = new ListNode(sum % 10); //获取结果
            cur = cur.next;
            //移动双指针
            if (left != null) {
                left = left.next;
            }
            if (right != null) {
                right = right.next;
            }
        }
        //最后判断是否尤进位
        if(carry>0){
            cur.next = new ListNode(carry);
        }

        return dummyHead.next;
    }

//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode dummyHead = new ListNode(0);//创建一个假头
//        ListNode p = l1, q = l2, curr = dummyHead;
//        int carry = 0;
//        while (p != null || q != null) {
//            //聪明啊，如果p为空，就说明后面高位没有值了，直接为0
//            int x = (p != null) ? p.val : 0;
//            int y = (q != null) ? q.val : 0;
//            int sum = carry + x + y; //sum = 进位+x+y
//            carry = sum / 10; //求取仅为
//            curr.next = new ListNode(sum % 10); //获取余数，就是的那个位的位数
//            curr = curr.next; //指针后裔
//            if (p != null) p = p.next; //不会空，后移，
//            if (q != null) q = q.next; //不为空，后移
//        }
//        //最后一位，如果仅为大于0，就把最后一位也添加到链表上
//        if (carry > 0) {
//            curr.next = new ListNode(carry);
//        }
//        return dummyHead.next;
//    }
}