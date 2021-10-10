package com.douma.written_test.zijie._20211010;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _4_Guess_Num {

    // 猜数字猜赢的概率
    // 时间复杂度：O(n^3)
    // 空间复杂度：O(n^2)
    // 建议先看看 leetcode 相似题：https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if (n == 1) {
            System.out.println(1);
        } else {
            // dp[i][j]：表示依次从 i 到 j 的数字作为分割点(猜的数)，Alice 赢得游戏的最大概率
            double[][] dp = new double[n + 1][n + 1];

            for (int i = 0; i <= n; i++) {
                // 只有 i 这一个数字的时候，肯定能猜对，概率为 1
                dp[i][i] = 1;
            }

            for (int j = 2; j <= n; j++) {
                for (int i = j - 1; i >= 1; i--) {
                    // 在区间 [i, j] 中选择每一个数字的概率
                    double prob = 1.0 / (j - i + 1);
                    // 算除了两端的每一个分割点
                    for (int k = i + 1; k <= j - 1; k++) {
                        dp[i][j] = Math.max(prob * (dp[i][k - 1] + dp[k + 1][j]), dp[i][j]);
                    }
                    // 算两端
                    dp[i][j] = Math.max(dp[i][j], prob * dp[i + 1][j]);
                    dp[i][j] = Math.max(dp[i][j], prob * dp[i][j - 1]);
                }
            }

            System.out.printf("%.6f", dp[1][n]);
        }

    }
}
