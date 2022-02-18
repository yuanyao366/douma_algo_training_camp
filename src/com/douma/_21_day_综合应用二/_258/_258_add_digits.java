package com.douma._21_day_综合应用二._258;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _258_add_digits {
    /* 258. 各位相加
    给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。

    示例:
    输入: 38
    输出：2
        3 + 8 = 11
        1 + 1 = 2
     */

    public int addDigits1(int num) {
        while (num >= 10) {
            num = totalSum(num);
        }
        return num;
    }

    private int totalSum(int num) {
        int total = 0;
        while (num != 0) {
            total += num % 10;
            num /= 10;
        }
        return total;
    }

    // 数学法
    // 解释：https://blog.csdn.net/weixin_41541562/article/details/106635899
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }


}
