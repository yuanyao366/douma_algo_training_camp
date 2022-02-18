package com.douma._7_day_排序算法._164;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _164_maximum_gap {
    // 排序
    public int maximumGap1(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;
        Arrays.sort(nums);
        int maxGap = 0;
        for (int i = 1; i < nums.length; i++) {
            maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
        }
        return maxGap;
    }

    // 基数排序
    public int maximumGap2(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;

        new RadixSorter().sort(nums); // O(n)

        int maxGap = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxGap = Math.max(maxGap, nums[i + 1] - nums[i]);
        }

        return maxGap;
    }

    // 桶排序
    public int maximumGap3(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;
        // 1. 找到最大最小值
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (max == min) return 0;

        int gap = (int)Math.ceil((double)(max - min)/(nums.length - 1));

        // 2. 初始化桶数组
        int bucketNum = nums.length;
        int[][] buckets = new int[bucketNum][2];
        for (int i = 0; i < bucketNum; i++) {
            buckets[i][0] = Integer.MAX_VALUE;
            buckets[i][1] = Integer.MIN_VALUE;
        }

        // 3. 将所有元素添加到对应的桶中
        for (int num : nums) {
            // bucketId 计算逻辑如何理解，请参考 issue：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I498BD
            int bucketId = (num - min) / gap;
            buckets[bucketId][0] = Math.min(buckets[bucketId][0], num);
            buckets[bucketId][1]= Math.max(buckets[bucketId][1], num);
        }

        // 4. 计算最大间隔
        int maxGap = 0;
        int prevBucketMax = min;
        for (int[] bucket : buckets) {
            if (bucket[0] == Integer.MAX_VALUE) continue;
            maxGap = Math.max(maxGap, bucket[0] - prevBucketMax);
            prevBucketMax = bucket[1];
        }

        return maxGap;
    }
}
