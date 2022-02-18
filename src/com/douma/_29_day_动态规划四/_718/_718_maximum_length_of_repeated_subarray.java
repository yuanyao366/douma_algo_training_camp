package com.douma._29_day_动态规划四._718;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _718_maximum_length_of_repeated_subarray {
    /* 718. 最长重复子数组
    给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。

    示例：
    输入：
        A: [1,2,3,2,1]
        B: [3,2,1,4,7]
    输出：3
    解释：
    长度最长的公共子数组是 [3, 2, 1] 。
     
    提示：
    1 <= len(A), len(B) <= 1000
    0 <= A[i], B[i] < 100

     */

    // 动态规划
    public int findLength(int[] A, int[] B) {
        int m = A.length, n = B.length;
        // dp[i][j]：表示 A 中前 i 个元素中和 B 的前 j 个元素中最长公共子数组长度
        int[][] dp = new int[m + 1][n + 1];

        int ans = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }

        return ans;
    }
}
