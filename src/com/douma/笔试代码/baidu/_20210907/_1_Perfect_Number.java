package com.douma.笔试代码.baidu._20210907;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 菲菲
 */
public class _1_Perfect_Number {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] nums = new int[t];
        for (int i = 0; i < t; i++) {
            nums[i] = scanner.nextInt();
        }

        for (int num : nums) {
            System.out.println(f(num));
        }
    }

    private static long f(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        int digitsLen = chars.length;
        for (int i = 0; i < digitsLen; i++) {
            char c = chars[i];
            if (c > '3') {
                // 如果数字大于 3，那么就将这一位设置为 3
                chars[i] = '3';
            } else if (c == '0') {
                // 1. 将前一位减 1
                int j = i - 1;
                while (j >= 0) {
                    chars[j] = (char) (chars[j] - 1);
                    // 如果符合下面的条件，则那么直接退出：
                    //  1. 前一位减 1 后，当前位仍然大于等于 1
                    //  2. 已经到达第一位
                    if (chars[j] >= '1' || j == 0) break;
                    // 如果前一位减 1 后，当前位等于 0 的话，那么设置为 3
                    // 然后继续将前一位减 1
                    else chars[j] = '3';
                    j--;
                }
                // 2. 将当前位以及后面所有位设置为 3
                while (i < digitsLen) {
                    chars[i++] = '3';
                }
                // 3. 如果第一位减去 1 后，等于 0 ，那么结果将从第二位开始
                if (chars[0] == '0') {
                    return Long.valueOf(new String(chars, 1, digitsLen - 1));
                }
                // 4. 不用再处理了
                break;
            }
        }
        return Long.valueOf(new String(chars));
    }
}
