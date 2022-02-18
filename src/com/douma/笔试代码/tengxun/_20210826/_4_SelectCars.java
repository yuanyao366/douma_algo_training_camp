package com.douma.笔试代码.tengxun._20210826;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 * 官方微信：bigdatatang01
 * 公众号：抖码课堂
 * @作者 : 老汤
 */
public class _4_SelectCars {

    // 时间复杂度：O(n)
    // 空间复杂度：O(n) 或者 O(logn)
    public static void main(String[] args) {
        // 1. 处理输入数据
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] speed = new int[n];
        for (int i = 0; i < n; i++) {
            speed[i] = scanner.nextInt();
        }

        // 2. 对 speed 升序排列
        Arrays.sort(speed);

        // 3. 滑动窗口求符合条件的最多车辆数
        int ans = 0;
        int left = 0, right = 0;
        while (right < n) {
            // 如果窗口内的最大值减去最小值超过了 10，那么缩减窗口大小
            while (speed[right] - speed[left] > 10) {
                left++;
            }

            // 如果窗口内最大值减去最小值不大于 10 ，那么：
            // 1. 更新符合条件的最大车辆数
            // 2. 扩大窗口
            ans = Math.max(ans, right - left + 1);
            right++;
        }

        System.out.println(ans);
    }
}
