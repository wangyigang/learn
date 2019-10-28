package com.wangyg.leetcode;

import org.junit.Test;

public class Leetcode112 {
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

    //二叉树路径总和
    class Solution {
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null)
                return false;

            if (root.left == null && root.right == null) {
                return sum - root.val == 0;
            }
            return hasPathSum(root.left, sum - root.val)//减去当前值
                    || hasPathSum(root.right, sum - root.val);
        }
    }

}
