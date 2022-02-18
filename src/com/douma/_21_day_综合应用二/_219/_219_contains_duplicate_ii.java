package com.douma._21_day_综合应用二._219;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _219_contains_duplicate_ii {
    /* 219. 存在重复元素 II
    给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
    使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。

    示例 1:
    输入: nums = [1,2,3,1], k = 3
    输出: true

    示例 2:
    输入: nums = [1,0,1,1], k = 1
    输出: true

    示例 3:
    输入: nums = [1,2,3,1,2,3], k = 2
    输出: false

     */

    // 线性查找(超时)
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            // 在 [0, i) 区间内线性查找 nums[i]
            for (int j = 0; j < i; j++) {
                if (nums[i] == nums[j] && i - j <= k) return true;
            }
        }
        return false;
    }

    // 哈希表
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 在 [0, i) 区间内哈希查找 nums[i]
            if (indexMap.containsKey(nums[i])) {
                int j = indexMap.get(nums[i]);
                if (i - j <= k) return true;
            }
            indexMap.put(nums[i], i);
        }
        return false;
    }

    // 滑动窗口
    // 时间复杂度：O(n*min(n, k)) 注意：k 有可能大于 n
    // 空间复杂度：O(1)
    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        int left = 0;
        int right = 0;

        while (right < nums.length) {
            left = Math.max(0, right - k);

            // 窗口内线性查找
            while (left < right) {
                if (nums[left] == nums[right]) return true;
                left++;
            }

            right++;
        }

        return false;
    }

    // 滑动窗口
    // 时间复杂度：O(n)
    // 空间复杂度：O(min(n, k))
    public boolean containsNearbyDuplicate4(int[] nums, int k) {
        int left = 0;
        int right = 0;

        Set<Integer> window = new HashSet<>();

        while (right < nums.length) {
            // 窗口内哈希查找
            if (window.contains(nums[right])) return true;
            window.add(nums[right]);

            if (window.size() > k) {
                window.remove(nums[left]);
                left++;
            }

            right++;
        }

        return false;
    }
}
