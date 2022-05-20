package com.douma.笔试代码.jd._20220416;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 小莹
 */
public class _1_等差数 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        while (T > 0) {
            String numStr = scanner.nextLine();
            // 输入数字的长度
            int numLen = numStr.length();
            // 输入数字的高位数值
            int topDigit = numStr.charAt(0) - '0';
            // 输入的数字
            int x = Integer.parseInt(numStr);
            if (numLen < 3) {
                // 如果输入数字的长度小于 3，那么输入的数字本身就是等差数
                System.out.println(x);
                T--;
                continue;
            }

            // 时间复杂度：O(9*9*n) ，n 是数字的平均长度
            // 从输入数字的高位数值开始，遍历所有的位差，找到第一个大于 x 的等差数
            for (int td = topDigit; td <= 9; td++) {
                boolean hasFound = false;
                // 位差的范围是 [-9...9]，
                for (int bitDiff = -9; bitDiff <= 9; bitDiff++) {
                    int bitDigit = td;
                    // 高位数值和位差得到的是负数，则跳过
                    if (bitDigit + bitDiff * numLen < 0) continue;

                    // 根据高位数值、长度以及位差拼接等差数
                    StringBuilder res = new StringBuilder();
                    for (int i = 0; i < numLen; i++) {
                        res.append(bitDigit);
                        bitDigit += bitDiff;
                    }
                    int num = Integer.parseInt(res.toString());

                    // 如果等差数大于等于 x，则输出
                    if (num >= x) {
                        System.out.println(num);
                        hasFound = true;
                        break;
                    }
                }
                if (hasFound) break;
            }
            T--;
        }
    }
}
