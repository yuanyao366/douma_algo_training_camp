package com.douma.笔试代码.zijie._20211010;

import java.util.*;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 小莹
 */
public class _3_Min_Max_Num {
    public static void main(String[] args) {
        /*
        huocai_nums 中的 key 是火柴数目，value 这个需要这个火柴数目的所有的数字
        huocai_nums = {2: [1], 3: [7], 4: [4], 5: [2, 3, 5], 6: [0, 6, 9], 7: [8]}
        n:      2   3   4   5   6   7   8       9       10      11      12      13      14      15
        max:    1   7   11  71  111 711 1111    7111    11111   71111   111111  711111  1111111 7111111
        min:    1   7   4   2   0   8   10      18      22      20      00      80      88      108

        当前第 i 个最大值，等于第 i - 2 个的最大值再加 "1"
        当前第 i 个最小值，minDp[i] = min((minDp[i - j] + minDp[j])的字符排序最小值)，其中 j 的取值范围是 [2...i - 2]
         */
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        // 求第 i 个最大值
        String[] maxDp = new String[n + 1];
        maxDp[2] = "1";
        maxDp[3] = "7";
        for (int i = 4; i <= n; i++) {
            maxDp[i] = maxDp[i - 2] + "1";
        }

        // 求第 i 个最小值
        String[] minDp = new String[n + 1];
        minDp[2] = "1";
        minDp[3] = "7";
        minDp[4] = "4";
        minDp[5] = "2";
        minDp[6] = "0";
        minDp[7] = "8";

        if (n <= 7) {
            System.out.println(maxDp[n]);
            System.out.println(minDp[n]);
            System.exit(0);
        }

        for (int i = 8; i <= n; i++) {
            for (int j = i - 2; j > 1; j--) {
                String tmp = minDp[i - j] + minDp[j];
                tmp = sortMinNum(tmp);
                if (minDp[i] == null ||
                        Integer.parseInt(tmp) < Integer.parseInt(minDp[i])) {
                    minDp[i] = tmp;
                }
                if (j <= i - j) break;
            }
        }

        if (maxDp[n].startsWith("0")) {
            System.out.println(0);
        } else {
            System.out.println(maxDp[n]);
        }

        if (minDp[n].startsWith("0")) {
            System.out.println(0);
        } else {
            System.out.println(minDp[n]);
        }
    }

    /*
    将字符串排序成最小整数，且开头不能有 0
    比如:
        字符串 1010 排序后就是 1001
        字符串 0100 排序后就是 1000
        字符串 9128 排序后就是 1289
     */
    private static String sortMinNum(String s) {
        char[] chars = s.toCharArray();
        StringBuilder nonZeros = new StringBuilder();
        for (char c : chars) {
            if (c != '0') nonZeros.append(c);
        }

        if (nonZeros.length() == 0) return s;

        char[] tmp = nonZeros.toString().toCharArray();
        Arrays.sort(tmp);
        if (nonZeros.length() == s.length()) {
            return new String(tmp);
        }

        StringBuilder res = new StringBuilder();
        res.append(tmp[0]);
        for (int i = 0; i < s.length() - nonZeros.length(); i++) {
            res.append('0');
        }
        for (int i = 1; i < tmp.length; i++) {
            res.append(tmp[i]);
        }

        return res.toString();
    }
}
