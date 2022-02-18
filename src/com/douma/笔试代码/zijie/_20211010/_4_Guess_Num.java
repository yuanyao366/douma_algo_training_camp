package com.douma.笔试代码.zijie._20211010;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _4_Guess_Num {

    // 猜数字猜赢的概率
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(n^2)
    // leetcode 相似题：https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if (n == 1) {
            System.out.println(1);
        } else {
            // dp[i][0]：表示依次有 i 个数字，Alice 先拿，然后赢得游戏的最大概率
            // dp[i][1]：表示依次有 i 个数字，Alice 后拿，然后赢得游戏的最大概率
            double[][] dp = new double[n + 1][2];

            dp[1][0] = 1;
            dp[2][0] = 0.5;

            dp[1][1] = 0;
            dp[2][1] = 0.5;

            for (int i = 3; i <= n; i++) {
                double prob = 1.0 / i;
                for (int j = 1; j < i; j++) {
                    // Alice 先拿
                    dp[i][0] = Math.max(dp[i][0], prob * (dp[j - 1][1] + dp[i - j][1]));
                    // Alice 后拿
                    dp[i][1] = Math.max(dp[i][1], prob * (dp[j - 1][0] + dp[i - j][0]));
                }
            }

            System.out.printf("%.6f", dp[n][0]);
        }

    }
}
