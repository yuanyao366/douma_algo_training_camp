package com.douma._10_day_栈和队列._224;

import java.util.ArrayDeque;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _224_basic_calculator {
    /* leetcode 224 号算法题：基本计算器
        给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。

        输入：s = "1 + 1"
        输出：2

        输入：s = " 2-1 + 2 "
        输出：3

        输入：s = "(1+(4+5+2)-3)+(6+8)"
        输出：23

        1 <= s.length <= 3 * 10^5
        s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
        s 表示一个有效的表达式
     */

    public int calculate(String s) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int preSign = 1;
        int num = 0;
        int res = 0;
        for (char c : s.toCharArray()) {
            if (c <= '9' && c >= '0') {
                num = num * 10 + (c - '0');
            } else if (c == '+') {
                res += (preSign * num);
                preSign = 1;
                num = 0;
            } else if (c == '-') {
                res += (preSign * num);
                preSign = -1;
                num = 0;
            } else if (c == '(') {
                stack.push(res);
                stack.push(preSign);
                preSign = 1;
                res = 0;
            } else if (c == ')') {
                res += preSign * num;
                res *= stack.pop();
                res += stack.pop();
                num = 0;
            }
        }
        return res + preSign * num;
    }

    // 没有括号的情况
    public int calculate1(String s) {
        int preSign = 1;
        int num = 0;
        int res = 0;
        for (char c : s.toCharArray()) {
            if (c <= '9' && c >= '0') {
                num = num * 10 + (c - '0');
            } else if (c == '+') {
                res += (preSign * num);
                preSign = 1;
                num = 0;
            } else if (c == '-') {
                res += (preSign * num);
                preSign = -1;
                num = 0;
            }
        }
        return res + preSign * num;
    }
}
