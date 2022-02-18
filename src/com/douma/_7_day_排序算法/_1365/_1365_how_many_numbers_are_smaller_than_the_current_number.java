package com.douma._7_day_排序算法._1365;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1365_how_many_numbers_are_smaller_than_the_current_number {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;

        int[] cnt = new int[101];
        for (int num : nums) {
            cnt[num]++;
        }

        for (int i = 1; i < 101; i++) {
            cnt[i] += cnt[i - 1];
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
        }
        return res;
    }
    public int[] smallerNumbersThanCurrent2(int[] nums) {
        int n = nums.length;
        // 维护元素值 -> 索引关系
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            data[i][0] = nums[i];
            // bug 修复：第二个值存储索引
            data[i][1] = i;
        }

        // 按照元素值升序排序
        Arrays.sort(data, (o1, o2) -> o1[0] - o2[0]);

        int[] ans = new int[n];
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (prev == -1 || data[i][0] != data[i - 1][0]) {
                prev = i;
            }
            ans[data[i][1]] = prev;
        }

        return ans;
    }
    public int[] smallerNumbersThanCurrent1(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (nums[j] < nums[i]) {
                    cnt++;
                }
            }
            ret[i] = cnt;
        }
        return ret;
    }
}
