package com.douma._0_day;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class Solution {
    // 这种方式对单元测试不够友好
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] + nums[j] == target) {
                    System.out.println(i + "," + j);
                }
            }
        }
    }
}
