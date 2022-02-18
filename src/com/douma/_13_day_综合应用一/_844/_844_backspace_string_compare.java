package com.douma._13_day_综合应用一._844;

import java.util.ArrayDeque;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _844_backspace_string_compare {
    /* 844. 比较含退格的字符串
    给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，
    判断二者是否相等，并返回结果。 # 代表退格字符。

    注意：如果对空文本输入退格字符，文本继续为空。

    输入：S = "ab#c", T = "ad#c"
    输出：true

    输入：S = "ab##", T = "c#d#"
    输出：true

    输入：S = "a##c", T = "#a#c"
    输出：true

    1 <= S.length <= 200
    1 <= T.length <= 200
    S 和 T 只含有小写字母以及字符 '#'。

    进阶：你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
     */

    public boolean backspaceCompare1(String s, String t) {
        return build(s).equals(build(t));
    }

    // 重建字符串
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(m + n)
    private String build1(String str) {
        StringBuilder newS = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c != '#') {
                newS.append(c);
            } else if (newS.length() > 0) {
                newS.deleteCharAt(newS.length() - 1);
            }
        }
        return newS.toString();
    }

    // 栈
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(m + n)
    private String build2(String str) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (c != '#') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                stack.pop();
            }
        }
        return stack.toString();
    }

    // 双指针（从前往后遍历）
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(1)
    private String build(String str) {
        char[] chars = str.toCharArray();
        int slow = -1, fast = 0;
        while (fast < chars.length) {
            if (chars[fast] != '#') {
                slow++;
                if (slow != fast) swap(chars, slow, fast);
            } else if (slow > -1) {
                slow--;
            }
            fast++;
        }
        return slow == -1 ? "" : new String(chars, 0, slow + 1);
    }

    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    // 双指针（从后往前遍历）
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(1)
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;
        while (i >= 0 || j >= 0) {
            // 回退 S 字符串的字符
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }

            // 回退 T 字符串的字符
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }

            // 判断 S 和 T 是否相等
            // 如果当前的 i 和 j 对应的字符不相等
            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) return false;
            // 有一个指针到头了，还有一个不到头，则返回 false
            if ((i >= 0) != (j >= 0)) return false;

            i--;
            j--;
        }
        return true;
    }
}
