package com.douma._9_day_哈希查找._49;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _49_group_anagrams {
    /*  leetcode 49 号算法题：字母异位词分组
        给定一个字符串数组，将字母异位词组合在一起。
        字母异位词指字母相同，但排列不同的字符串。

        输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
        输出:
        [
            ["bat"],              --> abt
            ["eat", "tea", ate"], --> aet
            ["tan", "nat"]        --> ant
        ]

        - 所有输入均为小写字母。
        - 不考虑答案输出的顺序。
     */

    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            int[] count = new int[26];
            for (char c : chars) count[c - 'a']++;
            String key = Arrays.toString(count);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
