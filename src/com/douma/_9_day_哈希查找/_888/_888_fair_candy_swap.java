package com.douma._9_day_哈希查找._888;

import java.util.HashSet;
import java.util.Set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _888_fair_candy_swap {
    /* leetcode 888 号算法题：公平的糖果棒交换
    爱丽丝和鲍勃有不同大小的糖果棒：
        A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，
        B[j] 是鲍勃拥有的第 j 根糖果棒的大小。

    因为他们是朋友，所以他们想交换一根糖果棒，
    这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）

    返回一个整数数组 ans，其中
        ans[0] 是爱丽丝必须交换的糖果棒的大小，
        ans[1] 是 Bob 必须交换的糖果棒的大小。

    如果有多个答案，你可以返回其中任何一个。保证答案存在。

    输入：A = [1,2], B = [2,3]
    输出： [1,2]

    输入：A = [1,2,5], B = [2,4]
    输出：[5,4]

    1 <= A.length <= 10000
    1 <= B.length <= 10000
    1 <= A[i] <= 100000
    1 <= B[i] <= 100000
    保证爱丽丝与鲍勃的糖果总量不同。
    答案肯定存在。

    思路：
        爱丽丝糖果总数：sumA
        鲍勃糖果总数：sumB
    爱丽丝给 x 大小糖果给鲍勃，鲍勃给 y 大小糖果给爱丽丝
    sumA - x + y = sumB - y + x
    => x = y + (sumA - sumB) / 2

    对于 B 中任意 y，只要 A 中存在一个数 x，满足 x = y + (sumA - sumB) / 2
    那么 (x，y) 就是一个解
     */

    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0;
        for (int a : A) sumA += a;
        int sumB = 0;
        for (int b : B) sumB += b;

        int delta = (sumA - sumB) / 2;

        Set<Integer> set = new HashSet<>();
        for (int num : A) set.add(num);

        int[] ans = new int[2];
        for (int y : B) {
            int x = y + delta;
            if (set.contains(x)) {
                ans[0] = x;
                // bug 修复：将 y 设置给第二个元素
                ans[1] = y;
                break;
            }
        }

        return ans;
    }
}
