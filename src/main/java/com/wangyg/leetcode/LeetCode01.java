package com.wangyg.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class LeetCode01 {
    @Test
    public void test(){
        int[] num = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(num, target)));
    }
    //方式一：
    public int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2]; //创建一个数组，用于返回信息
        for(int i=0; i<nums.length; ++i){
            for(int j=i+1; j<nums.length;++j){ //内层循环 索引在i后面开始
                if(nums[i]+ nums[j] == target){
                    arr[0] = i;
                    arr[1] = j;
                    break;
                }
            }
        }
        return arr;
    }
}
