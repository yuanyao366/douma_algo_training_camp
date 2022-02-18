package com.douma._9_day_哈希查找._41;

import java.util.HashSet;
import java.util.Set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _41_first_missing_positive {
    /*
    leetcode 41 号算法题：缺失的第一个正数

    给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

    输入：nums = [1,2,3]
    输出： 4

    输入：nums = [3,4,-1,1]
    输出： 2

    - 0 <= nums.length <= 300
    - -2^31 <= nums[i] <= 2^31 - 1

    进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
     */

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // 1. 将小于 0 的元素变成正数
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) nums[i] = n + 1;
        }

        // 2. 将索引为 nums[i] 的元素设置为负数
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        // 3. 找到第一个大于 0 的元素，返回这个元素对应的下标 i 再加 1
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) return i + 1;
        }

        return n + 1;
    }

    // 哈希查找
    public int firstMissingPositive2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        for (int i = 1; i <= nums.length; i++) {
            // 哈希查找
            if (!set.contains(i)) return i;
        }
        return nums.length + 1;
    }

    // 暴力解法 超时
    public int firstMissingPositive1(int[] nums) {
        for (int i = 1; i <= nums.length; i++) {
            boolean isExits = false;
            // 线性查找
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == i) isExits = true;
            }
            if (!isExits) return i;
        }
        return nums.length + 1;
    }
}
