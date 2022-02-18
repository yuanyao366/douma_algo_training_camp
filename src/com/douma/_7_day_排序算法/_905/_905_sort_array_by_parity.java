package com.douma._7_day_排序算法._905;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _905_sort_array_by_parity {
    // 快排分区的逻辑优化
    public int[] sortArrayByParity(int[] A) {
        int less = 0, great = A.length - 1;
        while (less < great){
            if (A[less] % 2 > A[great] % 2) {
                int tmp = A[less];
                A[less] = A[great];
                A[great] = tmp;
            }
            if (A[less] % 2 == 0) less++;
            if (A[great] % 2 == 1) great--;
        }
        return A;
    }
    // 快排分区的逻辑
    public int[] sortArrayByParity4(int[] A) {
        int less = 0, great = 0;
        for (; great < A.length ; great++) {
            if (A[less] % 2 > A[great] % 2) {
                int tmp = A[less];
                A[less] = A[great];
                A[great] = tmp;
            }
            if (A[less] % 2 == 0) less++;
        }
        return A;
    }
    public int[] sortArrayByParity3(int[] A) {
        int n = A.length;
        Integer[] tmp = new Integer[n];
        for (int i = 0; i < n; i++) tmp[i] = A[i];

        Arrays.sort(tmp, (o1, o2) -> o1 % 2 - o2 % 2);

        for (int i = 0; i < n; i++) A[i] = tmp[i];

        return A;
    }

    public int[] sortArrayByParity2(int[] A) {
        int[] ans = new int[A.length];

        int left = 0;
        int right = A.length - 1;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                ans[left] = A[i];
                left++;
            } else {
                ans[right] = A[i];
                right--;
            }
        }
        return ans;
    }

    public int[] sortArrayByParity1(int[] A) {
        int[] ans = new int[A.length];

        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                ans[count] = A[i];
                count++;
            }
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 1) {
                ans[count] = A[i];
                count++;
            }
        }
        return ans;
    }
}
