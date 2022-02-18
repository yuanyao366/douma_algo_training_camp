package com.douma._24_day_贪心算法一._316;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _316_remove_duplicate_letters {
    /* 316. 去除重复字母
    给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
    需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

    示例 1：
    输入：s = "bcabc"
    输出：abc

    示例 2：
    输入：s = "cbacdcbc"
    输出：acdb

    提示：
    1 <= s.length <= 10^4
    s 由小写英文字母组成

     */

    // 贪心 + 单调栈
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public String removeDuplicateLetters(String s) {
        // 1. 计算字符在字符串 s 中的最后索引
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        // 2. 维护单调栈
        Deque<Character> stack = new ArrayDeque<>();
        // 用于记录字符是否已经存在于栈中
        boolean[] exists = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (exists[c - 'a']) continue;

            // 条件：
            // (1). 当前字符小于栈顶字符
            // (2). 栈顶字符在当前字符的后面还会出现
            while (!stack.isEmpty() && stack.peek() > c
                    && lastIndex[stack.peek() - 'a'] > i) {
                char top = stack.pop();
                exists[top - 'a'] = false;
            }

            stack.push(c);
            exists[c - 'a'] = true;
        }

        // 3. 将栈中字符拼接成结果
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pollLast());
        }

        return res.toString();
    }
}
