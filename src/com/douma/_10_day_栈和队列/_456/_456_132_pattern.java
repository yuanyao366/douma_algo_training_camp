package com.douma._10_day_栈和队列._456;

import java.util.ArrayDeque;
import java.util.TreeMap;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _456_132_pattern {
    /* leetcode 456 号算法题：132 模式
        给你一个整数数组 nums ，数组中共有 n 个整数。
        132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，
        并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
        如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。

        输入：nums = [1,2,3,4]
        输出：false

        输入：nums = [3,1,4,2]
        输出：true

        输入：nums = [-1,3,2,0]
        输出：true

        n == nums.length
        1 <= n <= 10^4
        -10^9 <= nums[i] <= 10^9
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        int maxk = Integer.MIN_VALUE;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < maxk) return true;
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                maxk = stack.peek();
                stack.pop();
            }
            if (nums[i] > maxk) stack.push(nums[i]);
        }
        return false;
    }

    public boolean find132pattern4(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;

        // 维护一个前缀最小值数组，用于确定 nums[i]
        int[] minPrefix = new int[nums.length];
        minPrefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            minPrefix[i] = Math.min(minPrefix[i - 1], nums[i]);
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[n - 1]);
        // bug 修复：j 从倒数第二个元素开始
        for (int j = n - 2; j >= 1; j--) {
            if (nums[j] > minPrefix[j]) {
                while (!stack.isEmpty() && stack.peek() <= minPrefix[j]) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[j]) {
                    return true;
                }
                stack.push(nums[j]);
            }
        }
        return false;
    }

    public boolean find132pattern3(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        int numsi = nums[0];

        // 将所有右边的元素放到红黑树中
        TreeMap<Integer, Integer> numskMap = new TreeMap<>();
        for (int k = 2; k < n; k++) {
            numskMap.put(nums[k], numskMap.getOrDefault(nums[k], 0) + 1);
        }

        // O(nlogn)
        // bug 修复：j 到倒数第二个为止
        for (int j = 1; j < n - 1; j++) {
            if (nums[j] > numsi) {
                // 红黑树查找大于左边最小值(numsi)得元素
                Integer numsk = numskMap.ceilingKey(numsi + 1);
                if (numsk != null && numsk < nums[j]) return true;
            }

            // 维护最小的 nums[i]
            numsi = Math.min(numsi, nums[j]);

            // 将下一个元素从右边的集合中删除掉
            numskMap.put(nums[j + 1], numskMap.get(nums[j + 1]) - 1);
            if (numskMap.get(nums[j + 1]) == 0) numskMap.remove(nums[j + 1]);
        }
        return false;
    }

    public boolean find132pattern2(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        int numsi = nums[0];
        for (int j = 1; j < n; j++) {
            // 线性查找
            for (int k = j + 1; k < n; k++) {
                if (numsi < nums[k] && nums[k] < nums[j]) {
                    return true;
                }
            }
            // 维护最小的 nums[i]
            numsi = Math.min(numsi, nums[j]);
        }
        return false;
    }

    public boolean find132pattern1(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] < nums[k] && nums[k] < nums[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
