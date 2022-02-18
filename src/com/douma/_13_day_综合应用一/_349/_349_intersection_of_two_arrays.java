package com.douma._13_day_综合应用一._349;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _349_intersection_of_two_arrays {
    /* 349. 两个数组的交集
    给定两个数组，编写一个函数来计算它们的交集。

    输入：nums1 = [1,2,2,1], nums2 = [2,2]
    输出：[2]

    输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    输出：[9,4]

    输出结果中的每个元素一定是唯一的。
    我们可以不考虑输出结果的顺序。
     */

    // 线性查找
    // 时间复杂度：O(m*n)
    // 空间复杂度：O(min(m, n))
    public int[] intersection1(int[] nums1, int[] nums2) {
        Set<Integer> list = new HashSet<>();
        for (int num1 : nums1) {
            // 线性查找
            for (int num2 : nums2) {
                if (num1 == num2) {
                    // O(1)
                    // 哈希去重
                    list.add(num1);
                }
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for (int num : list) {
            res[i++] = num;
        }
        return res;
    }

    // 二分查找
    // 时间复杂度：O((m + n)logn)
    // 空间复杂度：O(min(m, n))
    public int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> list = new HashSet<>();

        Arrays.sort(nums2); // O(nlogn)

        for (int num1 : nums1) {
            // 二分查找 O(logn)
            if (contains(nums2, num1)) {
                list.add(num1);
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for (int num : list) {
            res[i++] = num;
        }
        return res;
    }

    private boolean contains(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return true;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    // 哈希查找
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(min(m, n) + n)
    public int[] intersection3(int[] nums1, int[] nums2) {
        Set<Integer> list = new HashSet<>();

        Set<Integer> set2 = new HashSet<Integer>();
        for (int num : nums2) {
            set2.add(num);
        }

        for (int num1 : nums1) {
            // 哈希查找 O(1)
            if (set2.contains(num1)) {
                list.add(num1);
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for (int num : list) {
            res[i++] = num;
        }
        return res;
    }

    // 排序去重
    // 时间复杂度：O(mlogm + nlogn)
    // 空间复杂度：O(m + n)
    public int[] intersection4(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        // 使用双指针去重
        int i = 0, j = 0;
        // 时间复杂度：O(max(m, n))
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                // 保证加入元素的唯一性
                if (index == 0 || res[index - 1] != nums1[i]) {
                    res[index++] = nums1[i];
                }
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOfRange(res, 0, index);
    }
}
