package com.douma._12_day_滑动窗口._209;

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
        // 这里需要注意的是 i 是前缀和数组的索引，而非原始数组的索引
        /*
            那么 i 为什么从 1 开始呢？
            假设 i 从 0 开始，那么 prefixSum[0] 表示是原数组前 0 个元素之和，这个没什么意义，因为都不构成区间
         */
        for (int i = 1; i < prefixSum.length; i++) {
            // 说明：这里为什么是 i - 1 呢？
            // 首先，prefixSum[i] 表达的是原数组中前 i 个元素之和
            // 那么，原始数组中区间 [i, j) 的区间和等于：prefixSum[j] - prefixSum[i - 1]
            // 因为 [i, j) 的区间和需要包含 i 对应的元素，所以，减掉的是 i 前面的 prefixSum
            // 实际上我们要找的就是：prefixSum[j] - prefixSum[i - 1] >= target

            // 为什么 [i, j) 是右开的呢？
            // 因为 prefixSum[j] 表示的是原始数组前 j 个元素的和，前 j 个元素中不会包括索引为 j 的元素

            /*
            我们以下面的数组为例：
                      nums = [2,3,1,4, 4, 3]
               prefixSum = [0,2,5,6,10,14,17]
            i 是 从前缀和数组的第二个元素开始，即 i = 1
            这个时候比如 j = 3，
            那么 prefixSum[j] - prefixSum[i] = 4，这个是原始数组区间 [1, 2] 的区间和，即 [i, j) 的区间和
            prefixSum[j] - prefixSum[i - 1] = 6，这个是原始数组区间 [0, 2] 的区间和，即 [i - 1, j) 的区间和
            如果你是 prefixSum[j] - prefixSum[i] 的话，就会漏掉了第一个元素，所以这里必须是 i - 1
             */
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
