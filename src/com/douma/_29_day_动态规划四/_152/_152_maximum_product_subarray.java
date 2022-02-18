package com.douma._29_day_动态规划四._152;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _152_maximum_product_subarray {
    /* 152. 乘积最大子数组
    给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），
    并返回该子数组所对应的乘积。

    示例 1:
    输入: [2,3,-2,4]
    输出: 6
    解释: 子数组 [2,3] 有最大乘积 6。

    示例 2:
    输入: [-2,0,-1]
    输出: 0
    解释: 结果不能为 2, 因为 [-2,-1] 不是连续子数组。

     */

    // 动态规划
    public int maxProduct1(int[] nums) {
        int length = nums.length;
        int[] maxP = new int[length];
        int[] minP = new int[length];

        maxP[0] = nums[0];
        minP[0] = nums[0];
        int ans = nums[0];

        for (int i = 1; i < length; i++) {
            maxP[i] = Math.max(maxP[i - 1] * nums[i], Math.max(nums[i], minP[i - 1] * nums[i]));
            minP[i] = Math.min(minP[i - 1] * nums[i], Math.min(nums[i], maxP[i - 1] * nums[i]));
            ans = Math.max(ans, maxP[i]);
        }

        return ans;
    }

    // 动态规划 + 状态的压缩
    public int maxProduct(int[] nums) {
        int length = nums.length;
        int maxP = nums[0], minP = nums[0];
        int ans = nums[0];

        for (int i = 1; i < length; i++) {
            int mx = maxP, mn = minP;
            maxP = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minP = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(ans, maxP);
        }

        return ans;
    }
}
