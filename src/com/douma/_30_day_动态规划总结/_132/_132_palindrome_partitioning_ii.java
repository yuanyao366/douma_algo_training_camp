package com.douma._30_day_动态规划总结._132;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _132_palindrome_partitioning_ii {
    /* 132. 分割回文串 II
    给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。

    返回符合要求的 最少分割次数 。

    示例 1：
    输入：s = "aab"
    输出：1
    解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。

    示例 2：
    输入：s = "a"
    输出：0

    示例 3：
    输入：s = "ab"
    输出：1
     
    提示：
    1 <= s.length <= 2000
    s 仅由小写英文字母组成
     */

    public int minCut(String s) {
        int n = s.length();

        // 这里是在 leetcode 647 之上，做了点改变
        // 定义状态，dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
        boolean[][] dp = new boolean[n][n];
        // 状态初始化
        for (int i = 0; i < n; i++) {
            dp[i][i] = true; // 一个字符，肯定是回文
        }
        // 状态转移
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                // [i, j]
                boolean isCharEqual = s.charAt(i) == s.charAt(j);
                if (j - i == 1) { // 只有两个字符
                    dp[i][j] = isCharEqual;
                } else  { // 大于两个字符
                    dp[i][j] = isCharEqual && dp[i + 1][j - 1];
                }
            }
        }

        // f[i]：表示以 s[i] 结尾最少分割次数
        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            if (dp[0][i]) { // s[0...i] 是回文串，那么不需要分割
                f[i] = 0;
            } else {
                for (int j = 0; j < i; j++) {
                    if (dp[j + 1][i]) { // s[j + 1...i] 是回文串，那么可以不分割
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[n - 1];
    }
}
