package com.douma._25_day_贪心算法二._670;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _670_maximum_swap {
    /* 670. 最大交换
    给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。

    示例 1 :
    输入: 2736
    输出: 7236
    解释: 交换数字2和数字7。

    7623

    示例 2 :
    输入: 9973
    输出: 9973
    解释: 不需要交换。

    注意:
    给定数字的范围是 [0, 10^8]

     */

    // 暴力
    public int maximumSwap1(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int len = chars.length;

        int max = num;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                swap(chars, i, j);
                max = Math.max(max, Integer.parseInt(new String(chars)));
                swap(chars, i, j);
            }
        }

        return max;
    }

    private void swap(char[] charArray, int index1, int index2) {
        char temp = charArray[index1];
        charArray[index1] = charArray[index2];
        charArray[index2] = temp;
    }

    // 贪心策略：拿高位后面比高位大的值进行交换，而且越大越好
    public int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();

        int[] last = new int[10];
        for (int i = 0; i < chars.length; i++) {
            last[chars[i] - '0'] = i;
        }

        for (int i = 0; i < chars.length; i++) {
            for (int d = 9; d > chars[i] - '0'; d--) { // bug 修复：d--
                if (last[d] > i) {
                    char tmp = chars[i];
                    chars[i] = chars[last[d]];
                    chars[last[d]] = tmp;
                    return Integer.parseInt(new String(chars));
                }
            }
        }

        return num;
    }
}
