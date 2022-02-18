package com.douma._28_day_动态规划三._62;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _62_unique_paths {
    /* 62. 不同路径
    一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

    问总共有多少条不同的路径？

    提示：
        1 <= m, n <= 100
        题目数据保证答案小于等于 2 * 10^9
     */

    // 1. 记忆化搜索
    public int uniquePaths1(int m, int n) {
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(m, n, 0, 0, memo);
    }

    private int dfs(int m, int n, int i, int j, int[][] memo) {
        if (i == m - 1 && j == n - 1) return 1;
        if (i == m || j == n) return 0;

        if (memo[i][j] != -1) return memo[i][j];

        int left = dfs(m, n, i, j + 1, memo);
        int right = dfs(m, n, i + 1, j, memo);
        memo[i][j] = left + right;

        return memo[i][j];
    }

    // 2. 动态规划（左上到右下）
    public int uniquePaths2(int m, int n) {
        // dp[i][j]：表示从位置 [i, j] 到 [m - 1, n - 1] 的路径数
        int[][] dp = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 || j == n - 1) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
                }
            }
        }

        return dp[0][0];
    }

    // 3. 动态规划（左上到右下） - 状态压缩
    public int uniquePaths3(int m, int n) {
        // dp[i][j]：表示从位置 [i, j] 到 [m - 1, n - 1] 的路径数
        int[] dp = new int[n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 || j == n - 1) {
                    dp[j] = 1;
                } else {
                    dp[j] = dp[j + 1] + dp[j];
                }
            }
        }

        return dp[0];
    }

    // 4. 动态规划(右下到左上)
    public int uniquePaths4(int m, int n) {
        // dp[i][j]：表示从位置[0, 0] 到 [i, j] 的路径数
        int[][] dp = new int[m][n];

        // 状态转移
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else if (i != 0 && j != 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    // 动态规划(右下到左上) - 状态压缩
    public int uniquePaths5(int m, int n) {
        // dp[i][j]：表示从位置[0, 0] 到 [i, j] 的路径数
        int[] dp = new int[n];

        // 状态转移
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    dp[j] = 1;
                } else if (i != 0 && j != 0) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }

        return dp[n - 1];
    }
}
