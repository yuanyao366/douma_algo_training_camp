package com.douma._26_day_动态规划一._53;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _53_MaxSubArray01 {
    /* 53. 最大子序和
    给定一个整数数组 nums ，
    找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

    示例 1：
    输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
    输出：6
    解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。

    示例 2：
    输入：nums = [1]
    输出：1

    示例 3：
    输入：nums = [0]
    输出：0

    示例 4：
    输入：nums = [-1]
    输出：-1

    示例 5：
    输入：nums = [-100000]
    输出：-100000
     
    提示：
    1 <= nums.length <= 3 * 10^4
    -10^5 <= nums[i] <= 10^5

     */
    // 暴力解法
    // O(n^3)
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                // [i, j]
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int res = new _53_MaxSubArray01().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(res);
    }
}
