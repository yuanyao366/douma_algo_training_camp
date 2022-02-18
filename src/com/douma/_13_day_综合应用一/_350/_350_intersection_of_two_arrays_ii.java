package com.douma._13_day_综合应用一._350;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _350_intersection_of_two_arrays_ii {
    /* 350. 两个数组的交集 II
        给定两个数组，编写一个函数来计算它们的交集。

    输入：nums1 = [1,2,2,2], nums2 = [2,2]
    输出：[2,2]

    输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    输出：[4,9]

    输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
    我们可以不考虑输出结果的顺序。

    进阶：
    1. 如果给定的数组已经排好序呢？你将如何优化你的算法？
    2. 如果 nums1 的大小比 nums2 小很多，哪种方法更优？ --> 哈希查找
    3. 如果 nums2 的元素存储在磁盘上，内存是有限的，
        并且你不能一次加载所有的元素到内存中，你该怎么办？ --> 外部排序 + 双指针
     */

    // 哈希查找
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(min(m, n) + n)
    public int[] intersect1(int[] nums1, int[] nums2) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums2) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        for (int num : nums1) {
            if (countMap.containsKey(num) && countMap.get(num) > 0) {
                res[index++] = num;
                countMap.put(num, countMap.get(num) - 1);
            }
        }
        return Arrays.copyOfRange(res, 0 , index);
    }

    // 二分查找
    // 时间复杂度：O(mlogm + nlogn)
    // 空间复杂度：O(m + n)
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        int i = 0;
        // 时间复杂度：
        // 没有重复元素：O(mlogn)
        // 全部是重复元素：O(m + n)
        while (i < nums1.length) {
            int lowerBound = searchFirstTargetIndex(nums2, nums1[i]);
            if (lowerBound == -1) {
                i++;
                continue;
            }

            // 处理相同的元素
            // 在 nums2 中找到相同元素的个数
            int count = 0;
            while (lowerBound < nums2.length && nums2[lowerBound] == nums1[i]) {
                count++;
                lowerBound++;
            }

            // 在 nums1 中找到相同元素的个数
            int j = i;
            while (j < nums1.length && nums1[j] == nums1[i]) {
                j++;
                if (count > 0) {
                    res[index++] = nums1[i];
                    count--;
                }
            }

            i = j;
        }
        return Arrays.copyOfRange(res,0, index);
    }

    // 思路二：在循环体内排除没有目标值的区间
    private int searchFirstTargetIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] == target) return left;
        return -1;
    }

    // 排序查找
    // 时间复杂度：O(mlogm + nlogn)
    // 空间复杂度：O(m + n)
    public int[] intersect3(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        // 使用双指针去重
        int i = 0, j = 0;
        // 时间复杂度：O(max(m, n))
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                res[index++] = nums1[i];
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
