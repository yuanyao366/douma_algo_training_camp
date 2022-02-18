package com.douma._10_day_栈和队列._84;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _84_largest_rectangle_in_histogram {
    // 枚举宽
    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        for (int left = 0; left < heights.length; left++) {
            int minHeight = heights[left];
            for (int right = left; right < heights.length; right++) {
                minHeight = Math.min(minHeight, heights[right]);
                // [left, right]
                int currWidth = right - left + 1;
                ans = Math.max(ans, minHeight * currWidth);
            }
        }
        return ans;
    }

    // 枚举高
    public int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int ans = 0;
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];

            // 确定左右边界
            int left = mid, right = mid;
            while (left >= 0 && heights[left] >= height) left--;
            while (right < n && heights[right] >= height) right++;

            ans = Math.max(ans, height * (right - left - 1));
        }
        return ans;
    }

    // 枚举高 + 单调栈优化
    public int largestRectangleArea3(int[] heights) {
        int n = heights.length;
        // 1. 计算每根柱子左边第一个小于这根柱子的柱子(每根柱子的左边界)
        int[] left = new int[n];
        Arrays.fill(left, -1);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                left[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }
        // 2. 计算每根柱子右边第一个小于这根柱子的柱子(每根柱子的右边界)
        int[] right = new int[n];
        Arrays.fill(right, n);
        stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }

        int ans = 0;
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];
            ans = Math.max(ans, height * (right[mid] - left[mid] - 1));
        }
        return ans;
    }

    // 枚举高 + 单调栈优化（第二种方案）
    public int largestRectangleArea4(int[] heights) {
        int n = heights.length;
        // 1. 计算每根柱子左边第一个小于这根柱子的柱子(每根柱子的左边界)
        int[] left = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        // 2. 计算每根柱子右边第一个小于这根柱子的柱子(每根柱子的右边界)
        int[] right = new int[n];
        stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            right[i] = (stack.isEmpty() ? n : stack.peek());
            stack.push(i);
        }

        int ans = 0;
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];
            ans = Math.max(ans, height * (right[mid] - left[mid] - 1));
        }
        return ans;
    }

    // 枚举高 + 单调栈优化 + 一次遍历
    public int largestRectangleArea5(int[] heights) {
        int n = heights.length;
        // 1. 计算每根柱子左边第一个小于这根柱子的柱子(每根柱子的左边界)
        int[] left = new int[n];
        int[] right = new int[n];
        // bug 修复：将右边界都初始化为 n
        // 因为没有右边界的需要设置为 n
        Arrays.fill(right, n);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // 注意：如果出现 heights[i] == heights[stack.peek()] 的情况
            // 那么两个柱子计算出来的面试是一样大的，不管弹不弹出栈顶元素的话
            // 都会有一个柱子的面积计算小了，而另一个柱子面积计算正确
            // 因为我们求的是最大面积，所以这就够了
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        int ans = 0;
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];
            ans = Math.max(ans, height * (right[mid] - left[mid] - 1));
        }
        return ans;
    }
}
