package com.douma._30_day_动态规划总结._931;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _931_minimum_falling_path_sum {
    /* 931. 下降路径最小和
    给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。

    下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。
    在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
    具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。

    示例 1：
    输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
    输出：13
    解释：下面是两条和最小的下降路径，用加粗标注：
    [[2,1,3],      [[2,1,3],
     [6,5,4],       [6,5,4],
     [7,8,9]]       [7,8,9]]

    示例 2：
    输入：matrix = [[-19,57],[-40,-5]]
    输出：-59
    解释：下面是一条和最小的下降路径，用加粗标注：
    [[-19,57],
     [-40,-5]]

    示例 3：
    输入：matrix = [[-48]]
    输出：-48
     
    提示：
    n == matrix.length
    n == matrix[i].length
    1 <= n <= 100
    -100 <= matrix[i][j] <= 100

     */

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;

        // dp[i][j]：表示元素 [i, j] 的下降路径最小和
        int[][] dp = new int[n][n];

        // 最后一行所有元素的下降路径最小和就是元素值本身
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = matrix[n - 1][j];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                // dp[i][j] = min(dp[i + 1][j], dp[i + 1][j - 1], dp[i + 1][j + 1])
                int minSum = dp[i + 1][j];
                if (j > 0) minSum = Math.min(minSum, dp[i + 1][j - 1]);
                if (j + 1 < n) minSum = Math.min(minSum, dp[i + 1][j + 1]);

                dp[i][j] = minSum + matrix[i][j];
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int x : dp[0]) ans = Math.min(ans, x);

        return ans;
    }

}
