package com.douma.笔试代码.ali._20220407;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 小莹
 */
public class _1_进制转换 {

    static int MOD = 1000000007;

    public static void main(String[] args) {

        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 10);
        map.put('B', 11);
        map.put('C', 12);
        map.put('D', 13);
        map.put('E', 14);
        map.put('E', 15);

        Scanner scanner = new Scanner(System.in);
        String numStr = scanner.next();

        int[] res = new int[15];

        int n = numStr.length();
        // 时间复杂度：O(n)
        for (int i = 0; i < n; i++) {
            char c = numStr.charAt(n - i - 1);
            int num = map.getOrDefault(c, c - '0');
            for (int scale = 2; scale <= 16; scale++) {
                int index = scale - 2;
                if (res[index] == -1 || num >= scale) {
                    res[index] = -1;
                    continue;
                }
                res[index] = (res[index] + (num * myPow(scale, i)) % MOD) % MOD;
            }
        }

        Arrays.sort(res);
        for (int i = 0; i < 15; i++) {
            if (res[i] != -1) {
                System.out.println(res[i]);
            }
        }
    }

    // 见课程 B【刷题篇第 7 天】：力扣 50 题
    // 快速幂 + 迭代
    // 时间：O(32)
    // 空间：O(1)
    private static int myPow(int x, int n) {
        double res = 1;
        while (n != 0) {
            if ((n & 1) == 1) res *= x;
            x *= x;
            n >>= 1;
        }
        return (int)res % MOD;
    }

}
