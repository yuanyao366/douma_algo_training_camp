package com.douma._10_day_栈和队列._42;

import java.util.ArrayDeque;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _42_trapping_rain_water {
    public int trap(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;
        int ans = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.peek();
                stack.pop();
                if (stack.isEmpty()) break;

                int leftIndex = stack.peek();
                int currWidth = i - leftIndex - 1;
                int currHeight = Math.min(height[leftIndex], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }
    public int trap3(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;

        int leftMax = 0, rightMax = 0;
        // 注意：left 必须从 0 开始，right 必须从 n - 1 开始
        // 原因：第一根柱子或者最后一根柱子有可能是最大值
        int left = 0, right = n - 1;
        int ans = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                left++;
            } else {
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }

    public int trap2(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 1; i < height.length - 1; i++) {
            // 当前这个柱子能装的水的单位数等于 min(leftMax, rightMax) - height[i]
            int maxHeight = Math.min(leftMax[i], rightMax[i]);
            if (maxHeight > height[i])
                ans += maxHeight - height[i];
        }
        return ans;
    }
    public int trap1(int[] height) {
        if (height.length <= 2) return 0;

        int ans = 0;
        for (int i = 1; i < height.length - 1; i++) {
            // 求左边的最大值
            int leftMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }

            // 求右边的最大值
            int rightMax = 0;
            for (int j = i + 1; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            // 当前这个柱子能装的水的单位数等于 min(leftMax, rightMax) - height[i]
            int maxHeight = Math.min(leftMax, rightMax);
            if (maxHeight > height[i])
                ans += maxHeight - height[i];
        }
        return ans;
    }
}
