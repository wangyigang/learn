package com.wangyg.leetcode;

import org.junit.Test;
import org.scalatest.enablers.Definition;

public class Leetcode100 {

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
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if (p == null && q != null) {
                return false;
            }
            if (p != null && q == null) {
                return false;
            }
            //先比较根节点
            if(p.val!= q.val) return false;
            //递归比较左子树和右子树
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}
