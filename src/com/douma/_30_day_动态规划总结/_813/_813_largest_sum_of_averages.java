package com.douma._30_day_动态规划总结._813;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _813_largest_sum_of_averages {
    /* 813. 最大平均值和的分组
        我们将给定的数组 A 分成 K 个相邻的非空子数组 ，我们的分数由每个子数组内的平均值的总和构成。
        计算我们所能得到的最大分数是多少。

    注意我们必须使用 A 数组中的每一个数进行分组，并且分数不一定需要是整数。

    示例:
    输入:
    A = [9,1,2,3,9]
    K = 3
    输出: 20
    解释:
    A 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
    我们也可以把 A 分成[9, 1], [2], [3, 9].
    这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
    说明:

    1 <= A.length <= 100.
    1 <= A[i] <= 10000.
    1 <= K <= A.length.
    答案误差在 10^-6 内被视为是正确的。

     */

    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[] prefixSum = new double[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] += prefixSum[i - 1] + nums[i - 1];
        }

        // dp[i][j]: 表示将 nums 的前 i 个元素分割成 j 份得到的最大平均分数
        double[][] dp = new double[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            // 将 nums 的前 i 个元素分割成 1 份得到的最大分数
            dp[i][1] = prefixSum[i] / i;

            for (int j = 2; j <= k && j <= i; j++) {
                // 将 nums 的前 i 个元素分割成 j 份得到的最大平均分数等于：
                // 所有将前 l 个字符分割成 j - 1 份得到的最大平均分数，再加上 [l...i] 的平均值，求最大值
                for (int l = 1; l < i; l++) {
                    dp[i][j] = Math.max(dp[i][j], dp[l][j - 1] + (prefixSum[i] - prefixSum[l]) / (i - l));
                }
            }
        }

        return dp[n][k];
    }
}
