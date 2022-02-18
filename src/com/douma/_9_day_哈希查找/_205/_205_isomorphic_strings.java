package com.douma._9_day_哈希查找._205;

import java.util.HashMap;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _205_isomorphic_strings {
    /*
    leetcode 205 号算法题：同构字符串

    给定两个字符串 s 和 t，判断它们是否是同构的。

    如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。

    - 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。
    - 不同字符不能映射到同一个字符上，
    - 相同字符只能映射到同一个字符上，
    - 字符可以映射到自己本身。

    输入：s = "egg", t = "add"
    输出：true

    输入：s = "foo", t = "bar"
    输出：false

    输入：s = "paper", t = "title"
    输出：true

    - 可以假设 s 和 t 长度相同。
     */
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> s2tMap = new HashMap<>();
        Map<Character, Character> t2sMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            Character sc = s.charAt(i);
            Character tc = t.charAt(i);
            if ((s2tMap.containsKey(sc) && s2tMap.get(sc) != tc) ||
                    t2sMap.containsKey(tc) && t2sMap.get(tc) != sc) {
                return false;
            }
            s2tMap.put(sc, tc);
            t2sMap.put(tc, sc);
        }

        return true;
    }
}
