package com.douma._26_day_动态规划一._516;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _516_LongestPalindromeSubseq04 {
    // 动态规划
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int m = s.length();

        // 状态：dp[i][j] 表示在区间 [i...j] 中最长回文子序列的长度
        int[][] dp = new int[m][m];
        for (int i = 0; i < m; i++) {
            dp[i][i] = 1;
        }
        for (int interval = 1; interval < m; interval++) {
            for (int i = 0; i < m; i++) {
                int j = i + interval;
                if (j == m) break;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][m - 1];
    }

    public static void main(String[] args) {
        System.out.println(new _516_LongestPalindromeSubseq04()
                .longestPalindromeSubseq("bbbab"));
    }
}
