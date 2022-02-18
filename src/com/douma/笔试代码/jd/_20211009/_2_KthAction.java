package com.douma.笔试代码.jd._20211009;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 */
public class _2_KthAction {

    // k 次行动
    // 时间复杂度：O(k)
    // 空间复杂度：O(1)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int f = scanner.nextInt();
        int k = scanner.nextInt();

        // remain 表示剩余能量
        int remain = b, res = 0;
        boolean notArrive = false;
        for (int i = 0; i < k; i++) {
            // 从 0 到 a
            if (i % 2 == 0) {
                // 剩余容量到不了充能量站 f， 或者充满了能量，也到不了 a
                if (remain < f || b < a - f) {
                    notArrive = true;
                    break;
                } else {
                    // 满足两个条件就需要充能量：
                    // 1. 当前不是最后一趟，并且剩余能量不足以下一趟到达 f 点
                    // 2. 当前是最后一趟的话，那么剩余能量小于 a 的话，就需要充能量
                    if ((i != k - 1 && remain < a + (a - f)) || (i == k - 1 && remain < a)) {
                        res += 1;
                        // 注意，充完能量后，能量最多就是 b，因为容量是 b
                        // 充完后，只需要减去从 f 到 a 消耗的能量即可
                        remain = b - (a - f);
                    } else {
                        remain = remain - a;
                    }
                }
            } else { // 从 a 到 0
                // 剩余容量到不了充能量站 f， 或者充满了能量，也到不了 0
                if (remain < a - f || b < f) {
                    notArrive = true;
                    break;
                } else {
                    // 满足两个条件就需要充能量：
                    // 1. 当前不是最后一趟，并且剩余能量不足以下一趟到达 f 点
                    // 2. 当前是最后一趟的话，那么剩余能量小于 a 的话，就需要充能量
                    if ((i != k - 1 && remain < a + f) || (i == k - 1 && remain < a)) {
                        res += 1;
                        // 注意，充完能量后，能量最多就是 b，因为容量是 b
                        // 充完后，只需要减去从 f 到 0 消耗的能量即可
                        remain = b - f;
                    } else {
                        remain = remain - a;
                    }
                }
            }
        }

        if (notArrive) {
            System.out.println(-1);
        } else {
            System.out.println(res);
        }
    }
}
