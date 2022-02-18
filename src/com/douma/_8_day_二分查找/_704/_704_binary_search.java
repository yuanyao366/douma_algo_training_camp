package com.douma._8_day_二分查找._704;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _704_binary_search {

    // 思路二：在循环体内排除没有目标值的区间
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 剩余两个元素在循环体外处理
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }

    // 思路二：在循环体内排除没有目标值的区间
    public int search3(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid;
            else right = mid - 1;
        }
        if (nums[left] == target) return left;
        return -1;
    }

    // 思路二：在循环体内排除没有目标值的区间
    public int search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        if (nums[left] == target) return left;
        return -1;
    }

    // 思路一：在循环体内查找目标值
    public int search1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}
