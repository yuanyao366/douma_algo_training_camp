package com.douma.笔试代码.ali._20220407;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _3_军队移动 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        String s = scanner.nextLine();

        // 1. 统计每个字母出现的次数
        int[] cnt = new int[26];
        int kind = 0;
        for (int i = 0; i < n; i++) {
            int index = cnt[s.charAt(i) - 'a'];
            if (cnt[index] == 0) kind++;
            cnt[index]++;
        }

        if (kind < k) {
            System.out.println(0);
            System.exit(0);
        }

        int res = 0;
        int left =0, right = 0;
        while (right < n) {
            char c = s.charAt(right);
            cnt[c - 'a']--;

            
        }
    }

}
