package com.douma._9_day_哈希查找._560;

import java.util.HashMap;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _560_subarray_sum_equals_k {
    /* leetcode 560 号算法题：和为K的子数组

       给定一个整数数组和一个整数 k，
       你需要找到该数组中和为 k 的连续的子数组的个数。

       输入:nums = [1,1,1], k = 2
       输出: 2

       输入:nums = [0,1,-1,1,1,2], k = 0
       输出: 4

        - 数组的长度为 [1, 20,000]。
        - 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
     */

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int prefixSum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int diff = prefixSum - k;
            // 哈希查找
            if (map.containsKey(diff)) {
                res += map.get(diff);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return res;
    }

    // 前缀和 + 哈希查找
    public int subarraySum3(int[] nums, int k) {
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i -1];
        }

        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < prefixSum.length; i++) {
            int diff = prefixSum[i] - k;
            // 哈希查找
            if (map.containsKey(diff)) {
                res += map.get(diff);
            }
            map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0) + 1);
        }

        return res;
    }

    // 前缀和 + 线性查找
    public int subarraySum2(int[] nums, int k) {
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i -1];
        }

        int res = 0;
        for (int i = 0; i <= nums.length; i++) {
            int diff = prefixSum[i] - k;
            for (int j = 0; j < i; j++) {
                if (prefixSum[j] == diff) res++;
            }
        }
        return res;
    }

    // 暴力解法
    public int subarraySum1(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int m = i; m <= j; m++) {
                    sum += nums[m];
                }
                if (sum == k) res++;
            }
        }
        return res;
    }

    // 第二种思路 暴力优化
    public int subarraySum5(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) res++;
            }
        }
        return res;
    }


}
