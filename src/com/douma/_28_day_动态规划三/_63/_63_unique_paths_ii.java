package com.douma._28_day_动态规划三._63;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _63_unique_paths_ii {
    /* 63. 不同路径 II
    一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

    现在考虑网格中有障碍物，问总共有多少条不同的路径？

    提示：
    1 <= m, n <= 100
    每个格子中只能为 0 或 1
     */

    // 动态规划
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // 1. 状态定义
        // dp[i][j]：表示从点 [0, 0] 到达 [i, j] 的不同路径数
        int[][] dp = new int[m][n];

        // 2. 状态初始化
        // 2.1. 左上角元素
        if (obstacleGrid[0][0] == 0) {
            dp[0][0] = 1;
        }
        // 2.2：初始化第一列所有点到终点的路径数
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 0 && dp[i - 1][0] == 1) {
                dp[i][0] = 1;
            }
        }
        // 2.3：初始化第一行所有点到终点的路径数
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 0 && dp[0][i - 1] == 1) {
                dp[0][i] = 1;
            }
        }

        // 3. 状态转移方程
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    // 动态规划
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // 1. 状态定义
        // dp[i][j]：表示从点 [0, 0] 到达 [i, j] 的不同路径数
        int[][] dp = new int[m][n];

        // 2. 状态初始化

        // 3. 状态转移方程
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }

        return dp[m - 1][n - 1];
    }


    // 动态规划 + 状态压缩
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // 1. 状态定义
        // dp[i][j]：表示从点 [0, 0] 到达 [i, j] 的不同路径数
        int[] dp = new int[n];

        // 2. 状态初始化

        // 3. 状态转移方程
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[j] = 1;
                } else if (j == 0) {
                    dp[j] = dp[j];
                } else if (i == 0) {
                    dp[j] = dp[j - 1];
                } else {
                    dp[j] = dp[j - 1] + dp[j];
                }
            }
        }

        return dp[n - 1];
    }
}
