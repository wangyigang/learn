package com.wangyg.leetcode;

import org.junit.Test;
import org.scalatest.enablers.Definition;

public class Leetcode104 {

    @Test
    public void  test(){

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Solution {
        public int maxDepth(TreeNode root) {
            if(root == null) return 0;

            return 1+ Math.max(maxDepth(root.left), maxDepth(root.right));

        }
    }
}
