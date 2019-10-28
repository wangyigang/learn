package com.wangyg.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
给定一个二叉树，返回其节点值自底向上的层次遍历。
（即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

例如：
给定二叉树 [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
返回其自底向上的层次遍历为：
[
  [15,7],
  [9,20],
  [3]
]

 */
public class Leetcode107 {
    @Test
    public void test() {

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {

            List<List<Integer>> res = new ArrayList<>();
            if (root == null)
                return res;

            List<Integer> tmp = new ArrayList<>();
            res.add(tmp);
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> list = new LinkedList<>();

                for (int i = 0; i <size ; i++) {
                    //获取当前node
                    TreeNode node = queue.poll();
                    if(node!= null){
                        list.add(node.val);
                        queue.offer(node.left);
                        queue.offer(node.right);
                    }
                }
                if(list.size()>0)
                res.add(0,list);
            }

            return res;
        }
    }
}
