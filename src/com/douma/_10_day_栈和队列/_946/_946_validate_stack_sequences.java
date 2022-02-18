package com.douma._10_day_栈和队列._946;

import java.util.ArrayDeque;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _946_validate_stack_sequences {
    /* leetcode 946 号算法题：验证栈序列
        给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，
        只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；
        否则，返回 false 。

        输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
        输出： true
        解释：我们可以按以下顺序执行：
        push(1), push(2), push(3), push(4), pop() -> 4,
        push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1

        输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
        输出：false
        解释：1 不能在 2 之前弹出。

        0 <= pushed.length == popped.length <= 1000
        0 <= pushed[i], popped[i] < 1000
        pushed 是 popped 的排列
     */

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int i = 0;
        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() &&
                    i < popped.length
                    && popped[i] == stack.peek()) {
                stack.pop();
                i++;
            }
        }
        return i == popped.length;
    }
}
