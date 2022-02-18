package com.douma.笔试代码.meituan._20210903;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _2_no_ac {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String acs = scanner.next();

        int cCount = 0, res = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (acs.charAt(i) == 'c') {
                cCount++;
            } else {
                res += cCount;
            }
        }

        System.out.println(res);
    }
}
