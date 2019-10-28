package com.wangyg.leetcode;


import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
示例:

输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6

 */
public class LeetCode23 {
    @Test
    public void test(){
        ListNode head1 = new ListNode(1);
        ListNode node4= new ListNode(4);
        ListNode node5 = new ListNode(5);
        head1.next =  node4;
        node4.next= node5;
        ListNode head2 = new ListNode(1);
        ListNode node3= new ListNode(3);
        ListNode node42 = new ListNode(4);
        head2.next= node3;
        node3.next= node42;
        ListNode head3= new ListNode(2);
        ListNode node6 = new ListNode(6);
        head3.next= node6;

        ListNode[] listNodes = new ListNode[]{head1, head2, head3};
        Solution s =  new Solution();
        ListNode node = s.mergeKLists(listNodes);
        while(node!= null){
            System.out.println(node.val);
            node = node.next;
        }
    }


    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
//    class Solution {
//        public ListNode mergeKLists(ListNode[] lists) {
//            //使用优先级队列方式
//            PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));//使用自定义比较器
//
//            ListNode tmpHead = new ListNode(-1);
//            //将数据全部添加到lists中 //过滤掉空数据
//            priorityQueue.addAll(Stream.of(lists).filter(Objects::nonNull).collect(Collectors.toList())); //只有三个头结点
//            System.out.println(priorityQueue.toString());
//            ListNode tail = tmpHead;
//            while (!priorityQueue.isEmpty()) {
//                ListNode poll = priorityQueue.poll(); //然后每次都取第一个
//                tail.next = poll;
//                tail = tail.next;
//                if (poll.next != null) {
//                    priorityQueue.add(poll.next); //先加入头结点，然后再把剩下的节点加入
//                }
//            }
//
//            return tmpHead.next;
//        }
//
//    }

    class Solution{
        public ListNode mergeKLists(ListNode[] lists) {
            if(lists == null) return null;
            ListNode dummy = new ListNode(-1);
            ListNode tail = dummy;
            //创建优先级队列
            PriorityQueue<ListNode> queue =  new PriorityQueue<>(Comparator.comparingInt(o-> o.val)); //使用 o.val进行比较
            //使用java8方式添加到优先级队列中
            queue.addAll(Stream.of(lists).filter(Objects::nonNull).collect(Collectors.toList())); //添加所有 addAll
            //进行遍历
            while (!queue.isEmpty()) {
                ListNode peek = queue.poll(); //peek只是查看，不删除, poll删除
                tail.next = peek;
                tail = tail.next;
                if(peek.next!= null){
                    queue.add(peek.next);
                }
            }
            return dummy.next;
        }
    }

}
