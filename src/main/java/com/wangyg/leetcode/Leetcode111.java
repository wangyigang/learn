package com.wangyg.leetcode;

import org.junit.Test;

public class Leetcode111 {
    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        root.right = new TreeNode(9);


        TreeNode node20 = new TreeNode(20);
        node20.right = new TreeNode(15);
        node20.left = new TreeNode(7);

        root.left = node20;

        Solution solution = new Solution();
        System.out.println(solution.minDepth(root));
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    //二叉树最小的深度
    class Solution {
        //递归方式处理
        public int minDepth(TreeNode root) {
            if (root == null) return 0;

            if (root.left == null && root.right == null) return 1;
            //最小深度
            int min_depth = Integer.MAX_VALUE;

            //深度优先进行递归
            //向左进行递归
            if (root.left != null) {
                min_depth = Math.min(minDepth(root.left), min_depth);
            }

            //向右进行递归
            if (root.right != null) {
                min_depth = Math.min(minDepth(root.right), min_depth);
            }
            return min_depth + 1;
        }
    }
}
