package com.douma._0_day;

import org.junit.Assert;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class Solution1 {
    // 将功能放到一个方法中，使用测试框架进行单元测试
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;

        int[] res = new com.twq.Solution1().twoSum(nums, target);

        Assert.assertArrayEquals(new int[]{0, 1}, res);
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{j, i};
                }
            }
        }
        return new int[]{};
    }
}
