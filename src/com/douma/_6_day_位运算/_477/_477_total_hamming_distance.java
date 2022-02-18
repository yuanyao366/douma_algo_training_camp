package com.douma._6_day_位运算._477;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _477_total_hamming_distance {
    public int totalHammingDistance(int[] nums) {
        // 我们考虑数组中每个数二进制的第 i 位，
        // 假设一共有 t 个 1 和 n - t 个 0，
        // 那么显然在第 i 位的汉明距离的总和为 t * (n - t)
        int n = nums.length;
        // 存储所有元素对应位的 1 的个数
        int[] cnt = new int[32];
        for (int num : nums) {
            int i = 0;
            while (num > 0) {
                // 检查每个位是否为 1，并累加
                cnt[i] += num & 1;
                num >>= 1;
                i++;
            }
        }

        int res = 0;
        for (int k : cnt) {
            res += k * (n - k);
        }
        return res;
    }
    // 时间复杂度：O(n^2) ：超时
    public int totalHammingDistance1(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                res += hammingDistance(nums[i], nums[j]);
            }
        }
        return res;
    }

    public int hammingDistance(int x, int y) {
        // 使用异或计算 x 和 y 的不同位，结果中位为 1 ，说明这位不同
        int diff = x ^ y;

        // 计算 diff 中位 1 个数
        int res = 0;
        while (diff != 0) {
            diff = diff & (diff - 1);
            res++;
        }

        return res;
    }
}
