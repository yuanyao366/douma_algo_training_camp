package com.douma.笔试代码.huawei.rongyao._20210906;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _1_IDs {
    public static void main(String[] args) {
        long mod = 1000000007;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int N = scanner.nextInt();
            int L = scanner.nextInt();
            if (N == 0 && L == 0) break;

            long res = 0;
            // 时间复杂度：O(LlogN)
            for (int l = 1; l <= L; l++) {
                // 求 cnt = l^N
                long cnt = 1;
                int a = l;
                long b = N;
                while (a > 0) {
                    if (a % 2 == 1) cnt = (cnt * b) % mod;
                    b = (b * b) % mod;
                    a /= 2;
                }
                res = (res + cnt) % mod;
            }
            System.out.println(res);
        }
    }

}
