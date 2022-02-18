package com.douma._21_day_综合应用二._440;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _440_k_th_smallest_in_lexicographical_order {
    /* 440. 字典序的第 K 小数字
    给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。

    输入:
    n: 13   k: 2

    输出: 10
    字典序：1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9

    1,2,3,4,5,6,7,8,9,10,11,12,13

    “10” < “2”
    12 < 3
    110 < 2
    110 < 201
    110 < 12

    注意：1 ≤ k ≤ n ≤ 10^9。
     */

    // 排序
    // 超出内存限制
    public int findKthNumber1(int n, int k) {
        // 1. 将每个数字转成字符串
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(String.valueOf(i));
        }
        // 2. 对字符串进行排序
        Collections.sort(list);
        return Integer.valueOf(list.get(k - 1));
    }

    public int findKthNumber(int n, int k) {
        int curr = 1;
        k = k - 1;
        while (k > 0) {
            int nodes = calNodes(n, curr, curr + 1);
            if (nodes <= k) { // 不在当前的前缀中
                curr += 1;
                k -= nodes;
            } else { // 在当前的前缀中
                curr *= 10;
                k -= 1;
            }
        }
        return curr;
    }

    // 计算当前 curr 前缀树中小于 n 的节点数
    public int calNodes(int n, long curr, long next) {
        int nodes = 0;
        while (curr <= n) {
            /*
            你可能会问:咦？怎么是 n+1 ,而不是 n  呢？不是说好了 n  是上界吗？

            我举个例子，假若现在上界 n 为 12，算出以 1 为前缀的子节点数，
            首先 1 本身是一个节点，接下来要算下面 10，11，12，一共有 4 个子节点。

            那么如果用 Math.min(n, next)  - curr 会怎么样？

            这时候算出来会少一个，12 - 10 加上根节点，最后只有 3 个。因此我们务必要写 n+1。
            */
            nodes += Math.min(n + 1, next) - curr;
            curr *= 10;
            next *= 10;
        }
        return nodes;
    }
}
