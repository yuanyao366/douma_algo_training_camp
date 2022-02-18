package com.douma._21_day_综合应用二._202;

import java.util.HashSet;
import java.util.Set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _202_happy_number {
    /* 202. 快乐数
    编写一个算法来判断一个数 n 是不是快乐数。
    「快乐数」定义为：
        1. 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
        2. 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
        3. 如果 可以变为  1，那么这个数就是快乐数。

    如果 n 是快乐数就返回 true ；不是，则返回 false 。

    提示：
    1 <= n <= 2^31 - 1
     */

    // 此题的算法思想和 141 号环形链表一样的

    // 哈希表
    public boolean isHappy1(int n) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            if (n == 1) return true;
            if (set.contains(n)) return false;

            set.add(n);
            n = squareSum(n);
        }
    }

    // 双指针 - 快慢指针
    public boolean isHappy2(int n) {
        if (n == 1) return true;
        int slow = n;
        int fast = n;
        while (true) {
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
            if (slow == 1 || fast == 1) return true;
            if (slow == fast) return false;
        }
    }

    private int squareSum(int n) {
        int sum = 0;
        while (n != 0) {
            // bug 修复： % 10 是为了拿到个位数
            int num = n % 10;
            sum += num * num;
            n /= 10;
        }
        return sum;
    }
}
