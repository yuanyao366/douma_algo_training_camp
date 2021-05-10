package com.douma._8_day._1095;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1095_find_in_mountain_array {
    // 1. 找到峰顶元素索引
    private int searchPeakIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // 2. 在前半部分应用二分查找算法查找目标值（思路 2 实现）
    private int binarySearchFrontPart(int[] nums, int left, int peakIndex, int target) {
        while (left < peakIndex) {
            int mid = left + (peakIndex - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                peakIndex = mid;
            }
        }
        if (nums[left] == target) return left;
        return -1;
    }

    // 3. 在后半部分应用二分查找算法查找目标值（思路 2 实现）
    private int binarySearchLatterPart(int[] nums, int peakIndex, int right, int target) {
        while (peakIndex < right) {
            int mid = peakIndex + (right - peakIndex) / 2;
            if (target < nums[mid]) {
                peakIndex = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[peakIndex] == target) return peakIndex;
        return -1;
    }
}
