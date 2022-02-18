package com.douma.笔试代码.meituan._20210404;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
// issue 讨论链接：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4CNN1
public class _4_Grace_String_优化1 {

    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        char[] chars = str.toCharArray();
        int[] charCnt = new int[26];

        // 统计每个字符出现的个数
        for (char c : chars) {
            charCnt[c - 'a']++;
        }

        int res = 1;
        for (int i = 0; i < 26; i++) {
            res *= (charCnt[i] + 1);
            res %= 20210101;
        }

        System.out.println(res);
    }
}
