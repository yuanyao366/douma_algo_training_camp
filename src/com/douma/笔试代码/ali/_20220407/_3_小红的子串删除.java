package com.douma.笔试代码.ali._20220407;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 小莹
 */
public class _3_小红的子串删除 {

    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        String s = scanner.next();

        // 1. 统计每个字母出现的次数
        int[] cnt = new int[26];
        // 记录总共出现的字母种类
        int kind = 0;
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            cnt[index]++;
            if (cnt[index] == 1) kind++;
        }

        // 2. 如果总的字母种类都没有 k 大，那么直接退出
        if (kind < k) {
            System.out.println(0);
            System.exit(0);
        }

        int res = 0;
        int left = 0, right = 0;
        while (right < n) {
            // 删除 right 对应的字符
            char cRight = s.charAt(right);
            cnt[cRight - 'a']--;
            // 如果 right 字符出现的次数变为 0，则种类数减一
            if (cnt[cRight - 'a'] == 0) kind--;

            // 如果种类数小于 k ，那么说明不能删除 [left...right] 中所有的字符
            // 只能删除 [left...right - 1] 这个窗口的中字符
            if (kind < k) {
                // 在窗口 [left...right - 1] 中计算所有的连续子串的个数
                // 比如:
                // 这个窗口的字符串是 aab ，那么就有 6 个连续的子串 (3 * 4 / 2)
                // 这个窗口的字符串是 aaba ，那么就有 10 个连续的子串 (4 * 5 / 2)
                int m = right - left;
                res += m * (m + 1) / 2;

                // 移动 left，缩小窗口，恢复 [left...right - 1] 中的字符
                while (left < right) {
                    char cLeft = s.charAt(left);
                    cnt[cLeft - 'a']++;
                    if (cnt[cLeft - 'a'] == 1) kind++;
                    left++;
                }
            }

            // 如果可以删除的话，尽量的扩大窗口，找到最大的可以删除的连续子数组
            right++;
        }

        // 如果最后一个窗口不是空的话，那么这些字符串都是可以删除的
        if (kind >= k) {
            int m = right - left;
            res += m * (m + 1) / 2;
        }

        System.out.println(res);
    }

}
