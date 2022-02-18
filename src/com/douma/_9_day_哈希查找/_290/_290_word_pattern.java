package com.douma._9_day_哈希查找._290;

import java.util.HashMap;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _290_word_pattern {
    /* leetcode 290 号算法题：单词规律
    给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。

    这里的 遵循 指完全匹配，
    例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。

    输入: pattern = "abba", str = "dog cat cat dog"
    输出: true

    输入:pattern = "abba", str = "dog cat cat fish"
    输出: false

    输入: pattern = "aaaa", str = "dog cat cat dog"
    输出: false
     */

    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) return false;

        Map<Character, String> char2Word = new HashMap<>();
        Map<String, Character> word2Char = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            Character c = pattern.charAt(i);
            String word = words[i];
            // bug 修复：字符串需要使用 equals 方法比较
            if ((char2Word.containsKey(c) && !char2Word.get(c).equals(word)) ||
                    word2Char.containsKey(word) && !word2Char.get(word).equals(c)) {
                return false;
            }
            char2Word.put(c, word);
            word2Char.put(word, c);
        }

        return true;
    }
}
