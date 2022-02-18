package com.douma._12_day_滑动窗口.practice._159;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _159_longest_substring_with_at_most_two_distinct_characters {
    /* leetcode 159. 至多包含两个不同字符的最长子串
    给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。

    示例 1:
    输入: "eceba"
    输出: 3
    解释: t 是 "ece"，长度为3。

    示例 2:
    输入: "ccaabbb"
    输出: 5
    解释: t 是 "aabbb"，长度为5。
     */
    public int lengthOfLongestSubstringTwoDistinct1(String s) {
        int ans = 0;

        int left = 0, right = 0;
        // 维护当前窗口中每个字符出现的次数
        Map<Character, Integer> windowCharCnt = new HashMap<>();
        while (right < s.length()) {
            char currChar = s.charAt(right);
            windowCharCnt.put(currChar, windowCharCnt.getOrDefault(currChar, 0) + 1);

            while (left <= right && windowCharCnt.size() > 2) {
                char leftChar = s.charAt(left);
                windowCharCnt.put(leftChar, windowCharCnt.getOrDefault(leftChar, 0) - 1);
                if (windowCharCnt.get(leftChar) <= 0) {
                    windowCharCnt.remove(leftChar);
                }
                left++;
            }

            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int ans = 0;
        // 维护当前窗口中每个字符最后出现的索引
        Map<Character, Integer> charIndex = new HashMap<>();
        // 维护当前窗口中的
        int minIndex = -1;
        int left = 0, right = 0;
        while (right < s.length()) {
            char currChar = s.charAt(right);
            charIndex.put(currChar, right);

            while (left <= right && charIndex.size() > 2) {
                // 拿到窗口中最小的索引
                // 注意最小的索引值不一定等于 left 值，
                // 比如字符串 eeeetf ，那么它的 left 值可能是 0，但是窗口的最小索引值可能是 3
                int delIndex = Collections.min(charIndex.values());
                charIndex.remove(s.charAt(delIndex));
                left = delIndex + 1;
            }

            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }
}
