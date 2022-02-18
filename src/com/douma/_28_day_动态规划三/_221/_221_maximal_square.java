package com.douma._28_day_动态规划三._221;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _221_maximal_square {
    /* 221. 最大正方形
        在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。

        输入：matrix = [['1','0','1','0','0'],
                       ['1','0','1','1','1'],
                       ['1','1','1','1','1'],
                       ['1','0','0','1','0']]
        输出：4

        提示：
        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 300
        matrix[i][j] 为 '0' 或 '1'


     */

    public int maximalSquare1(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int ans = 0;

        // dp[i][j] 表示以 [i, j] 这个元素为右下角的最大的正方形的边长 
        int[][] dp = new int[m][n];

        // 第一列
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                ans = Math.max(ans, dp[i][0]);
            }
        }
        // 第一行
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                ans = Math.max(ans, dp[0][i]);
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i][j - 1],
                            Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }

        return ans * ans;
    }

    public int maximalSquare2(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int ans = 0;

        // dp[i][j] 表示以 [i, j] 这个元素为右下角的最大的正方形的边长 
        // 行的长度和列的长度都增加 1，有利于边界条件的处理
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i][j - 1],
                            Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
                    ans = Math.max(ans, dp[i][j]);
                } else {
                    // 对于以 0 为右下角的最大正方形边长设置为 0
                    // 这里可加可不加，因为 dp[i][j] 初始化的时候就是 0
                    dp[i][j] = 0;
                }
            }
        }

        return ans * ans;
    }

    // 状态压缩：压缩为一维数组
    // 计算当前的状态只依赖于上(preRow)、左上 (preRowPreCol) 以及左边 (dp[j - 1]) 三个状态
    // 对于 preRow 和 preRowPreCol 的计算逻辑请参考：
    // 课程 B 刷题篇第 26 天的力扣 1143 号算法题，视频讲解链接：https://ke.qq.com/course/3614291
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int ans = 0;

        // 状态压缩为一维数组 
        // 行的长度和列的长度都增加 1，有利于边界条件的处理
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int preRow = 0;
            int preRowPreCol = 0;
            for (int j = 1; j <= n; j++) {
                preRowPreCol = preRow;
                preRow = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(dp[j - 1],
                            Math.min(preRowPreCol, preRow)) + 1;
                    ans = Math.max(ans, dp[j]);
                } else {
                    // 对于以 0 为右下角的最大正方形边长设置为 0
                    // 这里必须加上，因为经过若干个循环， dp[j] 已经不等于 0 了
                    dp[j] = 0;
                }
            }
        }

        return ans * ans;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1','1','1','1','1'},
                {'1','1','1','1','1'},
                {'0','0','0','0','0'},
                {'1','1','1','1','1'},
                {'1','1','1','1','1'}
        };
        int ans = new _221_maximal_square().maximalSquare(matrix);
        System.out.println(ans);
    }
}
