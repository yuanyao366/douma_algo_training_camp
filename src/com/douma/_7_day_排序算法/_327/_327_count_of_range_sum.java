package com.douma._7_day_排序算法._327;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _327_count_of_range_sum {
    // 超时
    public int countRangeSum2(int[] nums, int lower, int upper) {
        int count= 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                // 必须是 long，要不然会溢出
                long sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum <= upper && sum >= lower) count++;
            }
        }
        return count;
    }


    // 超时
    public int countRangeSum3(int[] nums, int lower, int upper) {
        long[] prefixSum = new long[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int count= 0;
        for (int i = 0; i < prefixSum.length; i++) {
            for (int j = i + 1; j < prefixSum.length; j++) {
                // 必须是 long，要不然会溢出
                long sum = prefixSum[j] - prefixSum[i];
                if (sum <= upper && sum >= lower) count++;
            }
        }
        return count;
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefixSum = new long[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        long[] tmp = new long[prefixSum.length];
        return mergeSort(prefixSum, 0, prefixSum.length - 1, tmp, lower, upper);
    }

    private int mergeSort(long[] prefixSum, int lo, int hi, long[] tmp, int lower, int upper) {
        if (lo >= hi) return 0;
        int mid = lo + (hi - lo) / 2;

        int leftSumCount = mergeSort(prefixSum, lo, mid, tmp, lower, upper);
        int rightSumCount = mergeSort(prefixSum, mid + 1, hi, tmp, lower, upper);
        int count = 0;
        // 计算当前有效的区间和个数
        int i = lo;
        int l = mid + 1, r = mid + 1;
        while (i <= mid) {
            while (l <= hi && prefixSum[l] - prefixSum[i] < lower) l++;
            while (r <= hi && prefixSum[r] - prefixSum[i] <= upper) r++;
            count += (r - l);
            i++;
        }

        merge(prefixSum, lo, mid, hi, tmp);

        return leftSumCount + rightSumCount + count;
    }

    private void merge(long[] nums, int lo, int mid, int hi, long[] tmp) {
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
