package com.douma._9_day_哈希查找._389;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _389_find_the_difference {
    /* leetcode 389 号算法题：找不同
    给定两个字符串 s 和 t，它们只包含小写字母。
    字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
    请找出在 t 中被添加的字母。

    输入：s = "abcd", t = "baedc"
    输出：e

    输入：s = "ae", t = "aea"
    输出： a

    0 <= s.length <= 1000
    t.length == s.length + 1
    s 和 t 只包含小写字母
     */
    public char findTheDifference1(String s, String t) {
        int[] countS = new int[26];
        for (char c : s.toCharArray()) countS[c - 'a']++;
        for (char c : t.toCharArray()) {
            countS[c - 'a']--;
            if (countS[c - 'a'] < 0) return c;
        }
        return ' ';
    }

    public char findTheDifference2(String s, String t) {
        int as = 0, at = 0;
        for (char c : s.toCharArray()) as += c;
        for (char c : t.toCharArray()) at += c;
        return (char)(at - as);
    }

    public char findTheDifference(String s, String t) {
        int ret = 0;
        for (char c : s.toCharArray()) ret ^= c;
        for (char c : t.toCharArray()) ret ^= c;
        return (char)ret;
    }

}
