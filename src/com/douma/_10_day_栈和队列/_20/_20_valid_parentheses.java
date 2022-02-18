package com.douma._10_day_栈和队列._20;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _20_valid_parentheses {
    /*  leetcode 20 号算法题：有效的括号
        给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

        有效字符串需满足：
        1. 左括号必须用相同类型的右括号闭合。
        2. 左括号必须以正确的顺序闭合。

        输入：s = "()"
        输出：true

        输入：s = "([)]"
        输出：false

        输入：s = "()[]{}"
        输出：true

        1 <= s.length <= 10^4
        s 仅由括号 '()[]{}' 组成
     */

    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public boolean isValid(String s) {
        if (s.length() % 2 == 1) return false;

        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (c != map.get(top)) return false;
            }
        }
        return stack.isEmpty();
    }

    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public boolean isValid3(String s) {
        if (s.length() % 2 == 1) return false;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        return stack.isEmpty();
    }

    // 只有小括号的场景
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public boolean isValid1(String s) {
        if (s.length() % 2 == 1) return false;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public boolean isValid2(String s) {
        if (s.length() % 2 == 1) return false;
        int leftBraceCnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftBraceCnt++;
            } else {
                if (leftBraceCnt == 0) return false;
                leftBraceCnt--;
            }
        }
        return leftBraceCnt == 0;
    }
}
