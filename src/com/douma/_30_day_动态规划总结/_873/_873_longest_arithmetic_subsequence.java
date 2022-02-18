package com.douma._30_day_动态规划总结._873;

import java.util.HashMap;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _873_longest_arithmetic_subsequence {
    /* 873. 最长的斐波那契子序列的长度
    如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
        1. n >= 3
        2. 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}

    给定一个严格递增的正整数数组形成序列 arr，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回 0 。

    （回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。
    例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）

    示例 1：
    输入: arr = [1,2,3,4,5,6,7,8]
    输出: 5
    解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。

    示例 2：
    输入: arr = [1,3,7,11,12,14,18]
    输出: 3
    解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。

    提示：
    3 <= arr.length <= 1000
    1 <= arr[i] < arr[i + 1] <= 10^9

     */

    // 经典 LIS 问题
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;

        Map<Integer, Integer> indexes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexes.put(arr[i], i);
        }

        int ans = 0;
        // dp[i][j]：表示以 arr[i]，arr[j] 为结尾的最长的斐波那契子序列的长度
        int[][] dp = new int[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < j; i++) {
                int arrk = arr[j] - arr[i];
                // 在 [0...i] 中找到一个元素 arr[k]，使得 arr[k] + arr[i] = arr[j]
                // 所以需要保证 arrk < arr[i]
                if (indexes.containsKey(arrk) && arrk < arr[i]) {
                    int k = indexes.get(arrk);
                    dp[i][j] = Math.max(dp[i][j], dp[k][i] + 1);
                    ans = Math.max(ans, dp[i][j] + 2);
                }
            }
        }
        if (ans < 3) return 0;
        return ans;
    }
}
