package com.douma._8_day_二分查找._35;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _35_search_insert_position {
    // 思路二：在循环体内排除没有目标值的区间
    public int searchInsert(int[] nums, int target) {
        if (nums == null) return -1;
        if (nums.length == 0) return 0;
        // 二分查找第一个大于等于 target 的索引
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // 思路一：在循环体内查找目标值
    public int searchInsert1(int[] nums, int target) {
        if (nums == null) return -1;
        if (nums.length == 0) return 0;
        if (target > nums[nums.length - 1]) return nums.length;
        // 二分查找第一个大于等于 target 的索引
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target <= nums[mid]) {
                if (mid == 0 || nums[mid - 1] < target) return mid;
                else right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
