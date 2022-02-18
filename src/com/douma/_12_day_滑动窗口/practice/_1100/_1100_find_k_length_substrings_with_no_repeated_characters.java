package com.douma._12_day_滑动窗口.practice._1100;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1100_find_k_length_substrings_with_no_repeated_characters {
    /* leetcode 1100. 长度为 K 的无重复字符子串
    给你一个字符串 S，找出所有长度为 K 且不含重复字符的子串，请你返回全部满足要求的子串的 数目。

    示例 1：
    输入：S = "havefunonleetcode", K = 5
    输出：6
    解释：
    这里有 6 个满足题意的子串，分别是：'havef','avefu','vefun','efuno','etcod','tcode'。

    示例 2：
    输入：S = "home", K = 5
    输出：0
    解释：
    注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。
     
    提示：
    1 <= S.length <= 10^4
    S 中的所有字符均为小写英文字母
    1 <= K <= 10^4

     */

    public int numKLenSubstrNoRepeats(String S, int K) {
        int len = S.length();
        if (len < K) return 0;

        int ans = 0;
        // 用于存储窗口中每个字符出现的次数
        int[] count = new int[26];

        // 维护滑动窗口
        int left = 0;
        int right = 0;
        while (right < len) {
            // 对 right 下的字符做计数
            int rightIndex = S.charAt(right) - 'a';
            count[rightIndex]++;

            // left 指针移动
            // 移动时机：right 指向的字符在当前窗口重复出现了
            // 移动策略：left 指向的字符从当前窗口中移除
            while (count[rightIndex] > 1) count[S.charAt(left++) - 'a']--;

            // 现在窗口中没有重复的字符了
            // 如果当前窗口的长度等于 K 的话，则找到了一个符合条件的子串
            if (right - left + 1 == K) {
                ans++;
                // 需要再次移动 left 指针，因为这里已经是符合的了，就要缩小窗口了
                count[S.charAt(left++) - 'a']--;
            }
            right++;
        }
        return ans;
    }
}
