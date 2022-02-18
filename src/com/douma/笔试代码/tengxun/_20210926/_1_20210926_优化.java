package com.douma.笔试代码.tengxun._20210926;

import java.util.*;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 小莹
 */
// issue 讨论：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4C16V
public class _1_20210926_优化 {

    // 时间复杂度：比 O(n^2) 要低很多
    // 空间复杂度：比 O(n^2) 要低很多
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 第一行是节点的个数
        int n = Integer.parseInt(scanner.nextLine());

        // 第二行是每个节点的值
        String[] datum = scanner.nextLine().split(" ");
        int[] data = new int[n + 1];
        for (int i = 0; i < n; i++) {
            data[i + 1] = Integer.parseInt(datum[i]);
        }

        // 第三行是每个节点的父亲节点的索引
        datum = scanner.nextLine().split(" ");
        int[] parents = new int[n + 1];
        parents[1] = 0; // 根节点没有父亲节点
        for (int i = 0; i < n - 1; i++) {
            parents[i + 2] = Integer.parseInt(datum[i]);
        }

        // 用一个 Map 维护祖先及其所有子节点的关系
        // key 是祖先的 id，value 是这个祖先的所有孩子
        Map<Integer, List<Integer>> ancestors = new HashMap<>();
        // 这里的时间复杂度：O(nlogn)，n 的大小最大为 10^5
        for (int i = 2; i <= n; i++) {
            int parent = parents[i];
            // 时间复杂度是：O(logn)，因为树的高度平均为 logn
            while (parent != 0) {
                putAncestors(parent, i, ancestors);
                parent = parents[parent];
            }
        }

        int res = 0;
        // 总的时间复杂度：低于 O(n^2)
        // 因为树的高度会尽量保持在 O(logn)，也就是树会尽量的平衡
        // 平衡的树一般有一半左右的叶子节点，所以可以作为祖先的节点的个数是 n/2
        for (int ancestor : ancestors.keySet()) {
            // 根节点作为祖先的话，那么孩子有 n - 1 个
            // 根节点以下的节点作为祖先的话，孩子节点的个数成倍数的减少
            List<Integer> children = ancestors.get(ancestor);
            for (int child : children) {
                // 优化为 O(1)
                if (isSquareNumber(data[ancestor] * data[child])) {
                    res++;
                }
            }
        }

        System.out.println(res);
    }

    private static void putAncestors(int ancestor, int child,
                                     Map<Integer, List<Integer>> ancestors) {
        if (ancestors.containsKey(ancestor)) {
            ancestors.get(ancestor).add(child);
        } else {
            List<Integer> children = new ArrayList<>();
            children.add(child);
            ancestors.put(ancestor, children);
        }
    }

    // O(1)
    private static boolean isSquareNumber(int num) {
        double a = Math.sqrt(num);
        int b = (int) a;
        return a - b == 0;
    }

    // TODO：这里使用位运算的话，时间复杂度可以优化到 O(1)，
    //  见：https://leetcode-cn.com/problems/valid-perfect-square/solution/csu-du-chao-guo-100an-wei-yun-suan-de-sh-c3cx/
    // 时间复杂度是：O(1)
    private static boolean isSquareNumber1(int num) {
        int x = 32768;
        //确定 sqrt(num) 二进制最高位位置
        while (x * x > num) x >>= 1;
        for (int base = x >> 1; base > 0; base >>= 1) {
            //该位为 1 的时候平方比 num 小则设为 1
            if (x + base < 46341 && (x + base) * (x + base) <= num)
                x += base;
        }
        return (x * x) == num;
    }
}
