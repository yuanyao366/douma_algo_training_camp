package com.douma._12_day.practice._485;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _485_max_consecutive_ones {
    /* leetcode 485. 最大连续 1 的个数
    给定一个二进制数组， 计算其中最大连续 1 的个数。

    示例：
    输入：[1,1,0,1,1,1]
    输出：3
    解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
     
    提示：
    输入的数组只包含 0 和 1 。
    输入数组的长度是正整数，且不超过 10,000。

     */
    public int findMaxConsecutiveOnes1(int[] nums) {
        int ans = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] == 1) {
                // 如果 1 少的话，可以这样做
                ans = Math.max(ans, right - left + 1);
            } else {
                left = right + 1;
            }
            right++;
        }
        return ans;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                // 如果 1 少的话，要这样做
                ans = Math.max(ans, right - left);
                left = right + 1;
            }
            right++;
        }
        return Math.max(ans, right - left);
    }
}
