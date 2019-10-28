package com.wangyg.leetcode;

import org.junit.Test;

public class Leetcode101 {

    @Test
    public  void test(){
        
    }


     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

     //
    class Solution {
        public boolean isSymmetric(TreeNode root) {
          //比较
              return  isMirrior(root, root);
        }

         private boolean isMirrior(TreeNode left, TreeNode right) {
            if(left == null && right!= null)return false;
            if(left != null && right == null) return false;

             return left.val == right.val && isMirrior(left.left, right.right) && isMirrior(left.right, right.left);
         }
     }
}
