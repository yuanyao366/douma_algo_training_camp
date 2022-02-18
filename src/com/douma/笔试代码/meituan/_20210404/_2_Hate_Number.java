package com.douma.笔试代码.meituan._20210404;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _2_Hate_Number {
    // issue 讨论：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4CCL0

    // 时间复杂度：O(n * sqrt(m))，其中 n 的规模是 10^5， m 的规模大小为 2 * 10^4
    // 总的数据规模最大是 10^7 ( = 10^5 * 10^2) ，
    // 也就是相当于数据规模为 10^7，时间复杂度为 O(n)，注意这里的 n = 10^7，这个是不会超时的
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String k = scanner.next();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int res = 0;
        for (int num : nums) { // O(n)
            if (getFactorStr(num).contains(k)) { // O(sqrt(m))
                res++;
            }
        }
        System.out.println(res);
    }

    // 得到一个数字 num 的所有的因子，并且拼接成字符串返回
    // 时间复杂度：O(sqrt(m)), m 的大小是 2 * 10^4
    private static String getFactorStr(int num) {
        StringBuilder res = new StringBuilder();
        int i;
        for (i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                res.append(i);
            }
        }
        // 注意，此时 i * i > num，所以要 i --
        i--;

        // 第二趟反向遍历，对 i 的初始值，还需要根据是否 i * i == num 做判断，避免重复
        if (i * i == num) {
            i = i - 1;
        }
        for (; i >= 1 ; i--) {
            if (num % i == 0) {
                res.append(num / i);
            }
        }
        return res.toString();
    }
}
