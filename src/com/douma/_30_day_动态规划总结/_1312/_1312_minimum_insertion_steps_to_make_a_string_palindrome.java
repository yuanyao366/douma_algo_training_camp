package com.douma._30_day_动态规划总结._1312;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1312_minimum_insertion_steps_to_make_a_string_palindrome {
    /* 1312. 让字符串成为回文串的最少插入次数
    给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。

    请你返回让 s 成为回文串的 最少操作次数 。

    「回文串」是正读和反读都相同的字符串。

    示例 1：
    输入：s = "zzazz"
    输出：0
    解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。

    示例 2：
    输入：s = "mbadm"
    输出：2
    解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。

    示例 3：
    输入：s = "leetcode"
    输出：5
    解释：插入 5 个字符后字符串变为 "leetcodocteel" 。
    示例 4：
    输入：s = "g"
    输出：0

    示例 5：
    输入：s = "no"
    输出：1
     
    提示：
    1 <= s.length <= 500
    s 中所有字符都是小写字母。

     */
    public int minInsertions(String s) {
        int n = s.length();

        // dp[i][j] 表示对于字符串 s 的子串 s[i:j]（这里的下标从 0 开始，并且 s[i:j] 包含 s 中的第 i 和第 j 个字符），
        // 最少添加的字符数量，使得 s[i:j] 变为回文串
        int[][] dp = new int[n][n];

        /*
        我们从外向内考虑 s[i:j]：

        如果 s[i] == s[j]，那么最外层已经形成了回文，我们只需要继续考虑 s[i+1:j-1]；

        如果 s[i] != s[j]，那么我们要么在 s[i:j] 的末尾添加字符 s[i]，要么在 s[i:j] 的开头添加字符 s[j]，
        才能使得最外层形成回文。如果我们选择前者，那么需要继续考虑 s[i+1:j]；如果我们选择后者，那么需要继续考虑 s[i:j-1]。

        因此我们可以得到如下的状态转移方程：

        dp[i][j] = min(dp[i + 1][j] + 1, dp[i][j - 1] + 1)                     if s[i] != s[j]
        dp[i][j] = min(dp[i + 1][j] + 1, dp[i][j - 1] + 1, dp[i + 1][j - 1])   if s[i] == s[j]
         */

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }
}
