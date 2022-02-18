package com.douma._21_day_综合应用二._217;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _217_contains_duplicate {
    /* 217. 存在重复元素
    给定一个整数数组，判断是否存在重复元素。

    如果存在一个值在数组中出现至少两次，函数返回 true 。
    如果数组中每个元素都不相同，则返回 false 。

    示例 1:
    输入: [1,2,3,1]
    输出: true

    示例 2:
    输入: [1,2,3,4]
    输出: false

    示例 3:
    输入: [1,1,1,3,3,4,3,2,4,2]
    输出: true

     */

    // 等值查找
    // 线性查找(超时)
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public boolean containsDuplicate1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 在 (i, nums.length) 区间内线性查找 nums[i]
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    // 线性查找(超时)
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public boolean containsDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 在 [0, i) 区间内线性查找 nums[i]
            for (int j = 0; j < i; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    // 哈希查找
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public boolean containsDuplicate3(int[] nums) {
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 在 [0, i) 区间内哈希查找 nums[i]
            if (visited.contains(nums[i])) {
                return true;
            }
            visited.add(nums[i]);
        }
        return false;
    }

    // 排序优化
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(logn) 或者 O(n)
    public boolean containsDuplicate4(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }
}
