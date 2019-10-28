package com.wangyg.leetcode;

import org.junit.Test;


public class Leetcode110 {
    @Test
    public void test(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        root.right = node20;
        node20.left = new TreeNode(15);
        node20.right = new TreeNode(7);

        Solution solution = new Solution();
        System.out.println(solution.isBalanced(root));
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //判断是否是平衡二叉树

    class Solution {
        boolean res = true;

        public boolean isBalanced(TreeNode root) {
            isBalancedHelper(root);
            return res;
        }

        private  int isBalancedHelper(TreeNode root) {
            if(root == null) return 0;

            int left = isBalancedHelper(root.left)+1;

            int right =isBalancedHelper(root.right)+1;

            if(Math.abs(right-left) >1) res = false;

            return Math.max(left, right);
        }
    }
}
