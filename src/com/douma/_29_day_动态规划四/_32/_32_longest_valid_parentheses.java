package com.douma._29_day_动态规划四._32;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _32_longest_valid_parentheses {
    /* 32. 最长有效括号
    给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。

     示例 1：
    输入：s = "(()"
    输出：2
    解释：最长有效括号子串是 "()"

    示例 2：
    输入：s = ")()())"
    输出：4
    解释：最长有效括号子串是 "()()"

    s = "(()))())"  --> 4

    示例 3：
    输入：s = ""
    输出：0
     

    提示：
    0 <= s.length <= 3 * 10^4
    s[i] 为 '(' 或 ')'

     */

    // 暴力解法，超时
    // 时间复杂度：O(n^3)
    // 空间复杂度：O(n)
    public int longestValidParentheses1(String s) {
        int maxlen = 0;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (isValid(s.substring(j, i))) {
                    maxlen = Math.max(maxlen, i - j);
                }
            }
        }
        return maxlen;
    }

    public boolean isValid(String s) {
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

    // 动态规划
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int longestValidParentheses2(String s) {
        int n = s.length();
        if (n <= 1) return 0;

        // 1. 状态，dp[i] 表示以下标为 i 的字符结尾的最长有效子字符串的长度
        int[] dp = new int[n];

        dp[0] = 0;
        // bug 修复：考虑第一个字符和第二个字符
        if (s.charAt(0) == '(' && s.charAt(1) == ')') dp[1] = 2;
        int res = dp[1];

        for (int i = 2; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else if (i - dp[i - 1] >= 1 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2
                            + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0);
                }
                res = Math.max(res, dp[i]);
            }
        }

        return res;
    }

    // 栈
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int longestValidParentheses3(String s) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }

    // 双变量
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else right++;

            if (left == right) {
                ans = Math.max(ans, 2 * left);
            } else if (right > left) {
                left = right = 0;
            }
        }

        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') left++;
            else right++;

            if (left == right) {
                ans = Math.max(ans, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return ans;
    }
}
