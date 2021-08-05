package com.douma._30_day._97;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _97_interleaving_string {
    /* 97. 交错字符串
    给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。

    两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
    s = s1 + s2 + ... + sn
    t = t1 + t2 + ... + tm
    |n - m| <= 1
    交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
    提示：a + b 意味着字符串 a 和 b 连接。

    示例 1：
    输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
    输出：true

    示例 2：
    输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
    输出：false

    示例 3：
    输入：s1 = "", s2 = "", s3 = ""
    输出：true
     
    提示：
    0 <= s1.length, s2.length <= 100
    0 <= s3.length <= 200
    s1、s2、和 s3 都由小写英文字母组成

     */

    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), t = s3.length();

        if (m + n != t) return false;

        // dp[i][j]：表示 s1 的前 i 个字符和 s2 的前 j 个字符是否交替组成 s3 的前 i + j 个字符
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i - 1))
                        || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1));
            }
        }

        return dp[m][n];
    }
}
