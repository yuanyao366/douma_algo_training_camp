package com.douma._12_day_滑动窗口._487;

public class _487_max_consecutive_ones_ii {
    /* leetcode 487. 最大连续 1 的个数 II
    给定一个二进制数组，你可以最多将 1 个 0 翻转为 1，找出其中最大连续 1 的个数。

    示例 1：
    输入：[1,0,1,1,0]
    输出：4
    解释：翻转第一个 0 可以得到最长的连续 1。
         当翻转以后，最大连续 1 的个数为 4。
     
    注：
    输入数组只包含 0 和 1.
    输入数组的长度为正整数，且不超过 10,000
     
    进阶：
    如果输入的数字是作为 无限流 逐个输入如何处理？
    换句话说，内存不能存储下所有从流中输入的数字。您可以有效地解决吗？
     */
    public int findMaxConsecutiveOnes1(int[] nums) {
        int ans = 0;
        int left = 0, right = 0;
        int windowZeroCnt = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                windowZeroCnt++;
                if (windowZeroCnt == 2) {
                    ans = Math.max(ans, right - left);
                }
            }
            while (windowZeroCnt == 2) {
                if (nums[left] == 0) windowZeroCnt--;
                left++;
            }

            right++;
        }
        return Math.max(ans, right - left);
    }

    public int findMaxConsecutiveOnes2(int[] nums) {
        int ans = 0;
        int left = 0, right = 0;
        int zeroIndex = -1; // 记录当前窗口中 0 出现的位置
        while (right < nums.length) {
            if (nums[right] == 0) {
                if (zeroIndex >= 0) { // 说明当前窗口已经有 0
                    ans = Math.max(ans, right - left);
                    left = zeroIndex + 1;
                }
                zeroIndex = right;
            }
            right++;
        }
        return Math.max(ans, right - left);
    }
}
