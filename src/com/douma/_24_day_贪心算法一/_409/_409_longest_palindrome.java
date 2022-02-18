package com.douma._24_day_贪心算法一._409;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _409_longest_palindrome {
    /* 409. 最长回文串
    给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。

    在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。

    注意:
    假设字符串的长度不会超过 1010。

    示例 1:
    输入:
    "abccccdd"
    输出: 7
    dccaccd

    输入:
    "ababbca"
    aaa
     */

    // 贪心策略：每次将这个字母的 v / 2 数放在回文串的左右两侧
    public int longestPalindrome(String s) {
        // 字符总的个数是 128，包含了大写字母和小写字母
        int[] counter = new int[128];
        for (char c : s.toCharArray()) {
            counter[c]++;
        }

        int ans = 0;
        for (int v : counter) {
            ans += v / 2 * 2;
            // 中间只能有一个出现奇数次的字符
            if (v % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }

        return ans;
    }

}
