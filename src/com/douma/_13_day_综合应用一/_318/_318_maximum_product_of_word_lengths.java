package com.douma._13_day_综合应用一._318;

import java.util.HashMap;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _318_maximum_product_of_word_lengths {
    /* 318. 最大单词长度乘积
    给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，
    并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。
    如果不存在这样的两个单词，返回 0。

    输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
    输出: 16

    输入: ["a","aa","aaa","aaaa"]
    输出: 0
     */

    // 暴力解法
    // m 表示单词的平均长度，n 表示单词的个数
    // 时间复杂度：O(n^2 * m)
    // 空间复杂度：O(1)
    public int maxProduct1(String[] words) {
        int ans = 0;
        for (int i = 0; i < words.length; i++) {
            String word1 = words[i];
            for (int j = i + 1; j < words.length; j++) {
                String word2 = words[j];
                // 每个单词的 bitMask 会重复计算很多次
                if (!hasSameChar(word1, word2)) {
                    ans = Math.max(ans, word1.length() * word2.length());
                }
            }
        }
        return ans;
    }
    // O(m^2)
    private boolean hasSameChar1(String word1, String word2) {
        for (char c : word1.toCharArray()) {
            if (word2.indexOf(c) != -1) return true;
        }
        return false;
    }

    private boolean hasSameChar2(String word1, String word2) {
        int[] count = new int[26];
        for (char c : word1.toCharArray()) count[c - 'a'] = 1;
        for (char c : word2.toCharArray()) {
            if (count[c - 'a'] == 1) return true;
        }
        return false;
    }

    // O(m)
    private boolean hasSameChar(String word1, String word2) {
        int bitMask1 = 0, bitMask2 = 0;
        for (char c : word1.toCharArray()) bitMask1 |= (1 << (c - 'a'));
        for (char c : word2.toCharArray()) bitMask2 |= (1 << (c - 'a'));
        return (bitMask1 & bitMask2) != 0;
    }

    // 位运算 + 预计算
    // 时间复杂度：O((m + n)* n)
    // 空间复杂度：O(n)
    public int maxProduct2(String[] words) {
        // O(mn)
        int n = words.length;
        int[] masks = new int[n];
        for (int i = 0; i < n; i++) {
            int bitMask = 0;
            for (char c : words[i].toCharArray()) {
                bitMask |= (1 << (c - 'a'));
            }
            masks[i] = bitMask;
        }

        // O(n^2)
        int ans = 0;
        for (int i = 0; i < words.length; i++) {
            String word1 = words[i];
            for (int j = i + 1; j < words.length; j++) {
                String word2 = words[j];
                if ((masks[i] & masks[j]) == 0) {
                    ans = Math.max(ans, word1.length() * word2.length());
                }
            }
        }
        return ans;
    }

    // 位运算 + 预计算
    // 时间复杂度：O((m + n)* n)
    // 空间复杂度：O(n)
    public int maxProduct(String[] words) {
        // O(mn)
        Map<Integer, Integer> map = new HashMap<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            int bitMask = 0;
            for (char c : words[i].toCharArray()) {
                bitMask |= (1 << (c - 'a'));
            }
            // there could be different words with the same bitmask
            // ex. ab and aabb
            map.put(bitMask, Math.max(map.getOrDefault(bitMask, 0), words[i].length()));
        }

        // O(n^2)
        int ans = 0;
        for (int x : map.keySet()) {
            for (int y : map.keySet()) {
                if ((x & y) == 0) {
                    ans = Math.max(ans, map.get(x) * map.get(y));
                }
            }
        }
        return ans;
    }
}
