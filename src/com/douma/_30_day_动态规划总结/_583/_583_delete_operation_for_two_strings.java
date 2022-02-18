package com.douma._30_day_动态规划总结._583;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _583_delete_operation_for_two_strings {
    /* 583. 两个字符串的删除操作
    给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，
    每步可以删除任意一个字符串中的一个字符。

    示例：
    输入: "sea", "eat"
    输出: 2
    解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
     
    提示：
    给定单词的长度不超过500。
    给定单词中的字符只含有小写字母。
     */

    /*
    为了求得最少删除次数，我们可以求出串 word1 和串 word2 最长公共子序列，我们记为 lcs。
    如果我们能求得 lcs 的值，我们可以轻易地求出答案，为 m + n - 2 * lcs。
    这里 m 和 n 分别是给定字符串 word1 和 word2 的长度。

    问题转化为 LCS 问题
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符【最长公共子序列长度】
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return m + n - 2 *  dp[m][n];
    }
}
