package com.douma._30_day_动态规划总结._115;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _115_distinct_subsequences {
    /* 115. 不同的子序列
    给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。

        字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
        （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）

        题目数据保证答案符合 32 位带符号整数范围。

        示例 1：
        输入：s = "rabbbit", t = "rabbit"
        输出：3
        解释：
        如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
        rabbbit
        rabbbit
        rabbbit

        示例 2：
        输入：s = "babgbag", t = "bag"
        输出：5
        解释：
        如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
        babgbag
        babgbag
        babgbag
        babgbag
        babgbag

     */

    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) return 0;

        // dp[i][j]：表示 s 的前 i 个字符中组成 t 的前 j 个字符的最多个数
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            // s 的前 i 个字符中始终包含一个 空字符串
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    /*
                    s[i] == t[j]的时候, s[i] 可以选择自己是否跟 t[j]匹配
                        如果匹配，那么 dp[i][j] 其中一部分数量就是 dp[i-1][j-1]
                        如果选择不匹配（这样可以让前面的字符跟t[j]匹配，毕竟 t 短的,s 长) dp[i][j] 另外一部分就是 dp[i - 1][j]
                     */
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }
}
