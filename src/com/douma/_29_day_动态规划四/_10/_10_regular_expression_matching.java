package com.douma._29_day_动态规划四._10;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _10_regular_expression_matching {
    /* 10. 正则表达式匹配
    给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
        '.' 匹配任意单个字符
        '*' 匹配零个或多个前面的那一个元素
    所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

    示例 1：
    输入：s = "aa" p = "a"
    输出：false
    解释："a" 无法匹配 "aa" 整个字符串。

    示例 2:
    输入：s = "aa" p = "a*"
    输出：true
    解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。
    因此，字符串 "aa" 可被视为 'a' 重复了一次。

    示例 3：
    输入：s = "ab" p = ".*"
    输出：true
    解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。

    示例 4：
    输入：s = "aab" p = "c*a*b"
    输出：true
    解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。
    因此可以匹配字符串 "aab"。

    示例 5：
    输入：s = "mississippi" p = "mis*is*p*."
    输出：false
     
    提示：
    0 <= s.length <= 20
    0 <= p.length <= 30
    s 可能为空，且只包含从 a-z 的小写字母。
    p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
    保证每次出现字符 * 时，前面都匹配到有效的字符

     */

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // 状态定义：dp[i][j] 表示 s 的前 i 个字符和 p 的前 j 个字符是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 状态初始化
        // 空字符串和空字符串是匹配的
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            // 前前一个元素匹配空字符串并且当前字符是 * ，那么是匹配
            // 提示：这里不可以不用判断 i < 2，原因是：
            // 目中的提示最后一条说了，如果是 * 的话，那么前面肯定有字符
            if (p.charAt(i - 1) == '*' && (i < 2 || dp[0][i - 2])) {
                dp[0][i] = true;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    // 需要再往回看一个
                    // 注意，这里之所以不要判断 j 是否大于等于 2 的原因是：
                    // 题目中的提示最后一条说了，如果是 * 的话，那么前面肯定有字符
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }

        return dp[m][n];
    }
}
