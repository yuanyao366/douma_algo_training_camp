package com.douma._12_day_滑动窗口._1004;

public class _1004_max_consecutive_ones_iii {
    /* leetcode 1004. 最大连续 1 的个数 III
    给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。

    返回仅包含 1 的最长（连续）子数组的长度。

    示例 1：
    输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
    输出：6
    解释：
    [1,1,1,0,0,1,1,1,1,1,1]
    粗体数字从 0 翻转到 1，最长的子数组长度为 6。

    示例 2：
    输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
    输出：10
    解释：
    [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
    粗体数字从 0 翻转到 1，最长的子数组长度为 10。
     
    提示：
    1 <= A.length <= 20000
    0 <= K <= A.length
    A[i] 为 0 或 1 

     */

    public int longestOnes1(int[] nums, int k) {
        int ans = 0;
        int left = 0, right = 0;
        int windowZeroCnt = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                windowZeroCnt++;
                if (windowZeroCnt == k + 1) {
                    ans = Math.max(ans, right - left);
                }
            }
            while (windowZeroCnt > k) {
                if (nums[left] == 0) windowZeroCnt--;
                left++;
            }

            right++;
        }
        return Math.max(ans, right - left);
    }

    public int longestOnes(int[] nums, int k) {
        int ans = 0;
        int left = 0, right = 0;
        // 记录当前窗口中 1 的个数
        int oneCnt = 0;
        while (right < nums.length) {
            if (nums[right] == 1) oneCnt++;
            while ((right - left + 1) - oneCnt > k) {
                if (nums[left] == 1) oneCnt--;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }
}
