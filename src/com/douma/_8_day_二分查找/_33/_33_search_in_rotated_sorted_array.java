package com.douma._8_day_二分查找._33;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _33_search_in_rotated_sorted_array {
    // 时间复杂度：O(logn)，注意，视频中说时间复杂度是 O(n)，这是口误
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) return mid;

            if (nums[left] <= nums[mid]) { // 左边有序
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // 右边有序
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    // 上面的代码如果不理解的话，可以这样来理解：
    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) return mid;

            // 当只有两个元素的时候，left 和 mid 指向的都是第一个元素
            // 这个时候 left == mid，那么 [left, mid] 和 [mid + 1, right] 都只有一个元素
            if (nums[left] == nums[mid]) {
                if (target == nums[right]) return right;
                else return -1;
            }

            if (nums[left] < nums[mid]) { // 左边有序
                if (target == nums[left]) return left;

                if (target > nums[left] && target < nums[mid]) { // target 在左边
                    right = mid - 1;
                } else { // 否则 target 在右边
                    left = mid + 1;
                }
            } else { // 右边有序
                if (target == nums[right]) return right;

                if (target > nums[mid] && target < nums[right]) { // target 在右边
                    left = mid + 1;
                } else { // 否则 target 在左边
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
