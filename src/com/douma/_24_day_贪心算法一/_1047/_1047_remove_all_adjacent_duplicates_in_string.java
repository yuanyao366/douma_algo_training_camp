package com.douma._24_day_贪心算法一._1047;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1047_remove_all_adjacent_duplicates_in_string {
    /* 1047. 删除字符串中的所有相邻重复项
    给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
    在 S 上反复执行重复项删除操作，直到无法继续删除。
    在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。

    示例：
    输入："abbaca"
    输出："ca"

    输入："abcddcbaca"
    输出： abc

    提示：
    1 <= S.length <= 20000
    S 仅由小写英文字母组成。
     */

    // 栈
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public String removeDuplicates1(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) res.append(stack.pollLast());
        return res.toString();
    }

    // StringBuilder 模拟栈
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public String removeDuplicates2(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (res.length() > 0 && res.charAt(res.length() - 1) == c) {
                res.deleteCharAt(res.length() - 1);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    // 快慢指针
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public String removeDuplicates(String s) {
        char[] chars = s.toCharArray();

        int slow = -1, fast = 0;
        while (fast < s.length()) {
            if (slow >= 0 && chars[fast] == chars[slow]) {
                slow--;
            } else {
                slow++;
                chars[slow] = chars[fast];
            }
            fast++;
        }

        // bug 修复：需要将 chars 中的前 slow 个字符组成新的字符串
        return new String(chars, 0, slow + 1);
    }
}
