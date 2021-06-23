package com.douma._12_day._209;

public class _209_minimum_size_subarray_sum {
    /* leetcode 209. 长度最小的子数组
    给定一个含有 n 个正整数的数组和一个正整数 target 。

    找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 
    [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
    如果不存在符合条件的子数组，返回 0 。

    示例 1：
    输入：target = 7, nums = [2,3,1,2,4,3]
    输出：2

    解释：子数组 [4,3] 是该条件下的长度最小的子数组。
    示例 2：
    输入：target = 4, nums = [1,4,4]
    输出：1

    示例 3：
    输入：target = 11, nums = [1,1,1,1,1,1,1,1]
    输出：0
     
    提示：
    1 <= target <= 10^9
    1 <= nums.length <= 10^5
    1 <= nums[i] <= 10^5
     
    进阶：
    如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。

     */

    // 暴力解法
    // 时间复杂度：O(n^2)
    public int minSubArrayLen1(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    ans = Math.min(ans, j - i + 1);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    // 滑动窗口
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int minSubArrayLen2(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int windowSum = 0;
        while (right < nums.length) {
            windowSum += nums[right];
            while (windowSum >= target) {
                ans = Math.min(ans, right - left + 1);
                windowSum -= nums[left];
                left++;
            }
            right++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    // 前缀和
    // 时间复杂度：O(nlogn)
    public int minSubArrayLen(int target, int[] nums) {
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < prefixSum.length; i++) {
            // 说明：这里为什么是 i - 1 呢？
            // 首先，prefixSum[i] 表达的是原数组中前 i 个元素之和
            // 那么，原始数组中区间 [i, j] 的区间和等于：prefixSum[j] - prefixSum[i - 1]
            // 因为 [i, j] 的区间和需要包含 i 对应的元素，所以，减掉的是 i 前面的 prefixSum
            // 实际上我们要找的就是：prefixSum[j] - prefixSum[i - 1] >= target
            int t = target + prefixSum[i - 1];
            int j = firstGETargetElement(prefixSum, t);
            if (j < 0) continue;
            if (j <= nums.length) {
                ans = Math.min(ans, j - i + 1);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    // 大于等于某个数的第一个位置
    public int firstGETargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target <= nums[mid]) {
                // 符合下面的两个条件之一就返回 mid ：
                // 1. mid 是数组的第一个元素
                // 2. mid 前面的那个元素小于 target
                if (mid == 0 || nums[mid - 1] < target) return mid;
                else right = mid - 1;
            } else { // target > nums[mid]
                left = mid + 1;
            }
        }
        return -1;
    }
}
