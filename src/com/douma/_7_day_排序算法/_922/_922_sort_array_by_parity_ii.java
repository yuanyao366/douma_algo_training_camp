package com.douma._7_day_排序算法._922;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _922_sort_array_by_parity_ii {
    public int[] sortArrayByParityII(int[] A) {
        int n = A.length;
        int i = 0, j = 1;
        while (i < n) {
            // 如果当前偶数位置是奇数元素的话
            if (A[i] % 2 == 1) {
                // 那么在奇数位置上找到一个偶数，与之交换
                while (A[j] % 2 == 1) j += 2;

                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
            i += 2;
        }
        return A;
    }
    public int[] sortArrayByParityII1(int[] A) {
        int n = A.length;
        int[] ans = new int[n];
        int i = 0;
        for (int a : A) {
            if (a % 2 == 0) {
                ans[i] = a;
                i += 2;
            }
        }
        i = 1;
        for (int a : A) {
            if (a % 2 == 1) {
                ans[i] = a;
                i += 2;
            }
        }
        return ans;
    }
}
