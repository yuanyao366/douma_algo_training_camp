package com.douma._7_day_排序算法._912;

import java.util.Random;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _912_sort_an_array {
    // 归并排序
    public int[] sortArray1(int[] nums) {
        mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
        return nums;
    }

    private void mergeSort(int[] nums, int lo, int hi, int[] tmp) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;

        mergeSort(nums, lo, mid, tmp);
        mergeSort(nums, mid + 1, hi, tmp);

        merge(nums, lo, mid, hi, tmp);
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
    // 快排
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) return;
        int index = partition(nums, lo, hi);
        quickSort(nums, lo, index - 1);
        quickSort(nums, index + 1, hi);
    }
    // 二分切分
    private int partition(int[] nums, int lo, int hi) {
        int i = new Random().nextInt(hi - lo + 1) + lo; // 随机选一个作为 pivot
        swap(nums, i, hi);
        int pivot = nums[hi];
        int less = lo, great = lo;
        for (; great <= hi - 1 ; great++) {
            if (nums[great] < pivot) {
                swap(nums, less, great);
                less++;
            }
        }
        swap(nums, less, hi);
        return less;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 三路快排
    private void quickSort1(int[] nums, int lo, int hi) {
        if (lo >= hi) return;

        int j = new Random().nextInt(hi - lo + 1) + lo; // 随机选一个作为 pivot
        swap(nums, j, hi);
        int pivot = nums[hi];
        int less = lo, great = hi;
        int i = lo;
        while (i <= great) {
            if (nums[i] < pivot) {
                swap(nums, i, less);
                less++;
                i++;
            } else if (nums[i] > pivot) {
                swap(nums, i, great);
                great--;
            } else {
                i++;
            }
        }

        quickSort1(nums, lo, less - 1);
        quickSort1(nums, great + 1, hi);
    }
}
