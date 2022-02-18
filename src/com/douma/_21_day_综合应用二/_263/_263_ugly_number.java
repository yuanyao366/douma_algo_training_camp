package com.douma._21_day_综合应用二._263;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _263_ugly_number {
    /* 263. 丑数
    给你一个整数 n ，请你判断 n 是否为 丑数 。
    如果是，返回 true ；否则，返回 false 。

    丑数 就是只包含质因数 2、3 和/或 5 的正整数。

    示例 1：
    输入：n = 6
    输出：true
    解释：6 = 2 × 3

    示例 2：
    输入：n = 8
    输出：true
    解释：8 = 2 × 2 × 2

    示例 3：
    输入：n = 14
    输出：false
    解释：14 不是丑数，因为它包含了另外一个质因数 7 。

    示例 4：
    输入：n = 1
    输出：true
    解释：1 通常被视为丑数。

    提示：
    -2^31 <= n <= 2^31 - 1
     */

    // DFS
    public boolean isUgly1(int n) {
        if (n == 0) return false;
        return dfs(n);
    }

    // 判断整数 n 是否可以被 2、3、5 整除
    private boolean dfs(int n) {
        if (n == 1) return true;

        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            if (n % factor == 0 && dfs(n / factor)) {
                return true;
            }
        }
        return false;
    }

    // 迭代
    public boolean isUgly(int n) {
        if (n == 0) return false;
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) n /= factor;
        }
        return n == 1;
    }
}
