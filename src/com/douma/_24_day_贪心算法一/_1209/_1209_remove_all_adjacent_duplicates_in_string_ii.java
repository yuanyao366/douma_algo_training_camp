package com.douma._24_day_贪心算法一._1209;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1209_remove_all_adjacent_duplicates_in_string_ii {
    /* 1209. 删除字符串中的所有相邻重复项 II
    给你一个字符串 s，「k 倍重复项删除操作」将会从 s 中选择 k 个相邻且相等的字母，
    并删除它们，使被删去的字符串的左侧和右侧连在一起。
    你需要对 s 重复进行无限次这样的删除操作，直到无法继续为止。
    在执行完所有删除操作后，返回最终得到的字符串。

    本题答案保证唯一。

    示例 1：
    输入：s = "abcd", k = 2
    输出："abcd"
    解释：没有要删除的内容。

    示例 2：
    输入：s = "deeedbbcccbdaa", k = 3
    输出："aa"
    解释：
    先删除 "eee" 和 "ccc"，得到 "ddbbbdaa"
    再删除 "bbb"，得到 "dddaa"
    最后删除 "ddd"，得到 "aa"

    示例 3：
    输入：s = "pbbcggttciiippooaais", k = 2
    输出："ps"
     
    提示：
    1 <= s.length <= 10^5
    2 <= k <= 10^4
    s 中只含有小写英文字母。

     */

    class Pair {
        char ch;
        int cnt;
        public Pair(char ch, int cnt) {
            this.ch = ch;
            this.cnt = cnt;
        }
    }

    // 栈
    // 时间复杂度：O(n)
    // 时间复杂度：O(n)
    public String removeDuplicates(String s, int k) {
        Deque<Pair> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty() || s.charAt(i) != stack.peek().ch) {
                stack.push(new Pair(s.charAt(i), 1));
            } else {
                stack.peek().cnt++;
                if (stack.peek().cnt == k) {
                    stack.pop();
                }
            }
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair p = stack.pollLast();
            for (int i = 0; i < p.cnt; i++) {
                res.append(p.ch);
            }
        }

        return res.toString();
    }

    // 另一种思路讲解
    public String removeDuplicates1(String s, int k) {
        StringBuilder res = new StringBuilder(s);
        int[] count = new int[s.length()];
        for (int i = 0; i < res.length(); i++) {
            if (i == 0 || res.charAt(i) != res.charAt(i - 1)) {
                count[i] = 1;
            } else {
                count[i] = count[i - 1] + 1;
                if (count[i] == k) {
                    // 时间复杂度：O(n)
                    res.delete(i - k + 1, i + 1);
                    i = i - k;
                }
            }
        }
        return res.toString();
    }

    // 另一种思路讲解
    public String removeDuplicates2(String s, int k) {
        StringBuilder res = new StringBuilder(s);
        Deque<Integer> count = new ArrayDeque<>();
        for (int i = 0; i < res.length(); i++) {
            if (i == 0 || res.charAt(i) != res.charAt(i - 1)) {
                count.push(1);
            } else {
                int incremented = count.pop() + 1;
                if (incremented == k) {
                    // 时间复杂度：O(n)
                    res.delete(i - k + 1, i + 1);
                    i = i - k;
                } else {
                    count.push(incremented);
                }
            }
        }
        return res.toString();
    }

    // 另一种思路讲解
    // 快慢指针
    public String removeDuplicates3(String s, int k) {
        char[] chars = s.toCharArray();
        Deque<Integer> count = new ArrayDeque<>();
        int slow = 0;
        for (int fast = 0; fast < chars.length; fast++, slow++) {
            if (slow != fast) chars[slow] = chars[fast];
            if (slow == 0 || chars[slow] != chars[slow - 1]) {
                count.push(1);
            } else {
                int incremented = count.pop() + 1;
                if (incremented == k) {
                    slow = slow - k;
                } else {
                    count.push(incremented);
                }
            }
        }
        // bug 修复：因为字符串 s 的字符串数组 chars 修改了，所以相当于 s 变掉了
        // 所以不能使用 substring，而使用 new String 重新构建新字符串
        return new String(chars, 0, slow);
    }
}
