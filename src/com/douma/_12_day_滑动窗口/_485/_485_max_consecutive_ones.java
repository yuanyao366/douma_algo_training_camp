package com.douma._12_day_滑动窗口._485;

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
        int ones = 0;
        for (int num : nums) {
            if (num == 1) {
                ones++;
            } else {
                ans = Math.max(ans, ones);
                ones = 0;
            }
        }
        return Math.max(ans, ones);
    }

    public int findMaxConsecutiveOnes2(int[] nums) {
        int ans = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                // 如果 0 少的话，可以这样做
                ans = Math.max(ans, right - left);
                left = right + 1;
            }
            right++;
        }
        return Math.max(ans, right - left);
    }

    public int findMaxConsecutiveOnes(int[] nums) {
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

}
