package com.douma._7_day_排序算法._493;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _493_reverse_pairs {
    public int reversePairs(int[] nums) {
        int[] tmp = new int[nums.length];
        return mergeSort(nums, 0, nums.length - 1, tmp);
    }

    private int mergeSort(int[] nums, int lo, int hi, int[] tmp) {
        if (lo >= hi) return 0;
        int mid = lo + (hi - lo) / 2;

        int leftSumCount = mergeSort(nums, lo, mid, tmp);
        int rightSumCount = mergeSort(nums, mid + 1, hi, tmp);
        int count = 0;
        // 计算当前翻转对的个数
        int i = lo;
        int j = mid + 1;
        while (i <= mid) {
            while (j <= hi && (long)nums[i] > 2 * (long)nums[j]) j++;
            count += (j - mid - 1);
            i++;
        }

        merge(nums, lo, mid, hi, tmp);

        return leftSumCount + rightSumCount + count;
    }

    private void merge(int[] nums, int lo, int mid, int hi, int[] tmp) {
        for (int i = lo; i <= hi; i++) {
            tmp[i] = nums[i];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) nums[k] = tmp[j++];
            else if (j == hi + 1) nums[k] = tmp[i++];
            else if (tmp[i] <= tmp[j]) nums[k] = tmp[i++];
            else nums[k] = tmp[j++];
        }
    }
}
