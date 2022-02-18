package com.douma._28_day_动态规划三._97;

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

    提示：
        0 <= s1.length, s2.length <= 100
        0 <= s3.length <= 200
        s1、s2、和 s3 都由小写英文字母组成

     */

    // 问题转化为二维数组路径问题
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), t = s3.length();
        if (m + n != t) {
            return false;
        }

        // dp[i][j]：
        // s1 的前 i 个字符和 s2 的前 j 个字符是否可以交错组成 s3 的前 i + j 个字符
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;
        // 第一列只能往下走
        // bug 修复：需要等于 m，因为是前 m 个字符
        // s1 是竖着的
        for (int i = 1; i <= m; i++) {
            if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[i][0] = true;
            } else { // 不相符直接终止
                break;
            }
        }
        // 第一行只能往右走
        // bug 修复：需要等于 n，因为是前 n 个字符
        // s2 是横着的
        for (int i = 1; i <= n; i++) {
            if (s2.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[0][i] = true;
            } else { // 不相符直接终止
                break;
            }
        }

        // bug 修复：需要等于 m，因为是前 m 个字符
        // bug 修复：需要等于 n，因为是前 n 个字符
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int k = i + j;
                boolean s1Equals3 = s1.charAt(i - 1) == s3.charAt(k - 1) && dp[i - 1][j];
                boolean s2Equals3 = s2.charAt(j - 1) == s3.charAt(k - 1) && dp[i][j - 1];
                dp[i][j] = s1Equals3 || s2Equals3;
            }
        }

        return dp[m][n];
    }
}
