package com.douma._10_day_栈和队列._739;

import java.util.ArrayDeque;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _739_daily_temperatures {
    /* leetcode 739 号算法题：每日温度
        请根据每日气温列表，重新生成一个列表。
        对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
        如果气温在这之后都不会升高，请在该位置用 0 来代替。

        输入：temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
        输出：[1, 1, 4, 2, 1, 1, 0, 0]

        1. 气温列表长度的范围是 [1, 30000]。
        2. 每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数
     */

    // 单调栈
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        if (n == 1) return new int[]{0};

        int[] res = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int x = T[i];
            // 单调递减栈
            while (!stack.isEmpty() && x > T[stack.peek()]) {
                // bug 修复：拿到栈顶元素，并弹出
                int prevIndex = stack.pop();
                res[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return res;
    }

    // 暴力解法
    public int[] dailyTemperatures1(int[] T) {
        if (T.length == 1) return new int[]{0};

        int[] res = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            for (int j = i + 1; j < T.length; j++) {
                if (T[j] > T[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }

        return res;
    }
}
