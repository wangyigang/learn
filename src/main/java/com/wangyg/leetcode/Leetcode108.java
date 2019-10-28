package com.wangyg.leetcode;


import org.junit.Test;

/*
将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:

给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5
 */
public class Leetcode108 {
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
        public TreeNode sortedArrayToBST(int[] nums) {
            // 左右等分建立左右子树，中间节点作为子树根节点，递归该过程
            //新创建一个函数，进行递归，参数： 数组， 左值，右值
            return nums == null ? null : buildTree(nums, 0, nums.length - 1);
        }

        private TreeNode buildTree(int[] nums, int left, int right) {
            if (left > right) {
                return null;
            }
            int m = (left+ right)/2;
            TreeNode root = new TreeNode(nums[m]);
            //递归查找左子树
            root.left = buildTree(nums, left, m - 1);
            //递归查找右子树
            root.right = buildTree(nums, m + 1, right); //二分法，从数组中位数到最右侧范围内进行查找
            return root;
        }
    }
}
