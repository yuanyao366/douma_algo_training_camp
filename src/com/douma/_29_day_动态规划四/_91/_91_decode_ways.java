package com.douma._29_day_动态规划四._91;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _91_decode_ways {
    /* 91. 解码方法
       一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：

    'A' -> 1
    'B' -> 2
    ...
    'Z' -> 26
    要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。
    例如，"11106" 可以映射为：
    "AAJF" ，将消息分组为 (1 1 10 6)
    "KJF" ，将消息分组为 (11 10 6)

    注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，
    这是由于 "6" 和 "06" 在映射中并不等价。

    给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。

    题目数据保证答案肯定是一个 32 位 的整数。

    示例 1：
    输入：s = "12"
    输出：2
    解释：它可以解码为 "AB"（1 2）或者 "L"（12）。

    示例 2：
    输入：s = "226"
    输出：3
    解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。

    示例 3：
    输入：s = "0"
    输出：0
    解释：没有字符映射到以 0 开头的数字。
    含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
    由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。

    示例 4：
    输入：s = "06"
    输出：0
    解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
     
    提示：
    1 <= s.length <= 100
    s 只包含数字，并且可能包含前导零。

     */

    // 1. 记忆化搜索
    public int numDecodings(String s) {
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return dfs(s, 0, memo);
    }

    // 以第 i 个字符开头的子串能解码的个数
    private int dfs(String s, int i, int[] memo) {
        if (i == s.length()) return 1;

        if (memo[i] != -1) return memo[i];

        if (s.charAt(i) == '0') return 0;

        int res = 0;
        res += dfs(s, i + 1, memo);
        if (i < s.length() - 1) {
            int one = s.charAt(i + 1) - '0';
            int ten = (s.charAt(i) - '0') * 10;
            if (one + ten <= 26) {
                res += dfs(s, i + 2, memo);
            }
        }
        memo[i] = res;

        return memo[i];
    }

    // 2. 动态规划(从右往左)
    public int numDecodings2(String s) {
        int n = s.length();
        // dp[i]：表示以第 i 个字符开头的子串能解码的个数
        int[] dp = new int[n + 1];

        dp[n] = 1;

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) != '0') {
                dp[i] += dp[i + 1];
                if (i < n - 1) {
                    int one = s.charAt(i + 1) - '0';
                    int ten = (s.charAt(i) - '0') * 10;
                    if (one + ten <= 26) {
                        dp[i] += dp[i + 2];
                    }
                }
            }
        }
        return dp[0];
    }

    // 3. 记忆化搜索
    public int numDecodings3(String s) {
        int[] memo = new int[s.length() + 1];
        Arrays.fill(memo, -1);
        return dfs2(s, s.length(), memo);
    }

    // 以第 i 个字符结尾的子串能解码的个数
    private int dfs2(String s, int i, int[] memo) {
        if (i == 0) return 1;
        if (memo[i] != -1) return memo[i];

        int res = 0;
        if (s.charAt(i - 1) != '0') {
            res += dfs2(s, i - 1, memo);
        }
        if (i > 1 && s.charAt(i - 2) != '0') {
            int one = s.charAt(i - 1) - '0';
            int ten = (s.charAt(i - 2) - '0') * 10;
            if (one + ten <= 26) {
                res += dfs2(s, i - 2, memo);
            }
        }

        memo[i] = res;

        return memo[i];
    }

    // 4. 动态规划(从左往右)
    public int numDecodings4(String s) {
        int n = s.length();
        // dp[i]：表示以第 i 个字符结尾的子串能解码的个数
        int[] dp = new int[n + 1];

        dp[0] = 1;

        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            if (i > 1 && s.charAt(i - 2) != '0') {
                int one = s.charAt(i - 1) - '0';
                int ten = (s.charAt(i - 2) - '0') * 10;
                if (one + ten <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[n];
    }

}
