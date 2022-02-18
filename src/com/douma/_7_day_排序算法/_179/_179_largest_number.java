package com.douma._7_day_排序算法._179;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _179_largest_number {
    // 自定义排序
    private class LargestNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String s1 = a + b;
            String s2 = b + a;
            return s2.compareTo(s1);
        }
    }

    public String largestNumber1(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strs, new LargestNumberComparator());

        if (strs[0].equals("0")) {
            return "0";
        }

        String s = new String();
        for (String str: strs) {
            s += str;
        }

        return s;
    }

    // 自定义排序
    public String largestNumber2(int[] nums) {

        sort(nums, 0, nums.length - 1);

        StringBuilder sb = new StringBuilder();
        for (int str : nums) {
            sb.append(str);
        }
        if (sb.charAt(0) == '0') return "0";
        return sb.toString();
    }

    private void sort(int[] data, int lo, int hi) {
        if (lo >= hi) return;
        // 分区
        int pivot = data[hi];

        int less = lo;
        int great = hi;

        int i = lo;
        while (i <= great) {
            if ((data[i] + "" + pivot).compareTo(pivot + "" + data[i]) > 0) {
                swap(data, i, less);
                less++;
                i++;
            } else if ((data[i] + "" + pivot).compareTo(pivot + "" + data[i]) < 0) {
                swap(data, i, great);
                great--;
            } else {
                i++;
            }
        }

        sort(data, lo, less - 1);
        sort(data, great +1, hi);
    }

    private void swap(int[] strs, int i, int j) {
        int tmp = strs[i];
        strs[i] = strs[j];
        strs[j] = tmp;
    }
}
