package com.douma._26_day_动态规划一._72;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _72_MinEditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // bug 修复，如果有一个字符为空字符串，就可以提前返回另一个字符串的长度
        if (m * n == 0) return m + n;

        // dp[i][j] 表示 word1 前 i 个字符转换成 word2 前 j 个字符花的最少操作数[即编辑距离]
        int[][] dp = new int[m + 1][n + 1];

        // bug 修复：i 可以等于 m 或者 n
        for (int i = 0; i <= m; i++) { // 第一列
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) { // 第一行
            dp[0][i] = i;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insertNum = 1 + dp[i][j - 1];
                    int deleteNum = 1 + dp[i - 1][j];
                    int replaceNum = 1 + dp[i - 1][j - 1];
                    dp[i][j] = Math.min(insertNum, Math.min(deleteNum, replaceNum));
                }
            }
        }

        return dp[m][n];
    }
}
