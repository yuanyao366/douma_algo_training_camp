package com.douma._8_day_二分查找._153;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _153_find_minimum_in_rotated_sorted_array {
    /**
     * 暴力解法：遍历一遍数组，找到最小值
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public int findMin_1(int[] nums) {
        int minVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            minVal = Math.min(minVal, nums[i]);
        }
        return minVal;
    }

    /**
     * 暴力解法：遍历数组，但是找到了比前一位小的数字，就是最小值
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public int findMin_2(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return nums[i];
            }
        }
        return nums[0];
    }

    /**
     * 二分查找
     * 时间复杂度是： O(logn)
     * 空间复杂度是：O(1)
     * @param nums
     * @return
     */
    public int findMin_3(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
