package com.douma._30_day_动态规划总结._712;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _712_minimum_ascii_delete_sum_for_two_strings {
    /* 712. 两个字符串的最小ASCII删除和
    给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。

    示例 1:
    输入: s1 = "sea", s2 = "eat"
    输出: 231
    解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
    在 "eat" 中删除 "t" 并将 116 加入总和。
    结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。

    示例 2:
    输入: s1 = "delete", s2 = "leet"
    输出: 403
    解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
    将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
    结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
    如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。

    注意:
    0 < s1.length, s2.length <= 1000。
    所有字符串中的字符ASCII值在[97, 122]之间。

     */

    // 两个字符串的最小 ASCII 删除和等于两个字符串所有字母的和减去两个字符串最大公共子序列所有字母的和
    // 问题转化成 LCS 问题
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += s1.charAt(i);
        }
        for (int i = 0; i < n; i++) {
            sum += s2.charAt(i);
        }

        // dp[i][j]：s1 前 i 个字符和 s2 前 j 个字符【最长公共子序列的所有字母和】
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + s1.charAt(i - 1);
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return sum - 2 * dp[m][n];
    }
}
