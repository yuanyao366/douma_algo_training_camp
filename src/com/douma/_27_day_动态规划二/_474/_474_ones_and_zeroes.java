package com.douma._27_day_动态规划二._474;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _474_ones_and_zeroes {
    /* 474. 一和零
    给你一个二进制字符串数组 strs 和两个整数 m 和 n 。

    请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。

    如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。

    示例 1：
    输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
    输出：4
    解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
    其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。
    {"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。

    示例 2：
    输入：strs = ["10", "0", "1"], m = 1, n = 1
    输出：2
    解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
     
    提示：
    1 <= strs.length <= 600
    1 <= strs[i].length <= 100
    strs[i] 仅由 '0' 和 '1' 组成
    1 <= m, n <= 100

     */

    // 二维费用背包问题
    // 物品是字符串数组中的字符串，选择每个字符串有两个代价，分别是 0 的个数和 1 的个数
    // 两个代价都有最大值，0 的个数最多为 m，1 的个数最多为 n
    // 求选择字符串得到的最大子集的大小
    public int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j] 表示包含 i 个 0 和 j 个 1 的最大子集的大小
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < strs.length; i++) {
            int[] count = countzeroesones(strs[i]);
            int zeroes = count[0], ones = count[1];
            for (int zero = m; zero >= zeroes; zero--) {
                for (int one = n; one >= ones; one--) {
                    dp[zero][one] = Math.max(dp[zero][one],
                            dp[zero - zeroes][one - ones] + 1);
                }
            }
        }

        return dp[m][n];
    }

    private int[] countzeroesones(String s) {
        int[] c = new int[2];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i)-'0']++;
        }
        return c;
    }
}
