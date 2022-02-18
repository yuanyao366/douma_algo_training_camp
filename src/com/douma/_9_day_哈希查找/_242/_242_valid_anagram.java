package com.douma._9_day_哈希查找._242;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _242_valid_anagram {
    /*  leetcode 242 号算法题：有效的字母异位词
    给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

    输入: s = "anagram", t = "nagaram"
    输出: true

    输入: s = "rat", t = "car"
    输出: false

    - 你可以假设字符串只包含小写字母。
    - 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     */

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);

        return Arrays.equals(sChars, tChars);
    }


    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        // 1. 统计字符串 s 中每个字符出现的次数
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        // 2. 减去字符串 t 中字符出现的次数，如果出现的次数出现小于 0 的话，则返回 false
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) - 1);
            if (map.get(c) < 0) return false;
        }

        return true;
    }


    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] map = new int[26];
        for (char c : s.toCharArray()) map[c - 'a']++;
        for (char c : t.toCharArray()) {
            map[c - 'a']--;
            if (map[c - 'a'] < 0) return false;
        }
        return true;
    }
}
