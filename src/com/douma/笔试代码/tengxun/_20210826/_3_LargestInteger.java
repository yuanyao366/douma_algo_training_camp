package com.douma.笔试代码.tengxun._20210826;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _3_LargestInteger {
    // 时间复杂度：O(n)，n 是字符串的长度
    // 空间复杂度：O(1)
    public int largestNum(String s, int k) {
        int n = s.length();
        int ans = Integer.MIN_VALUE;
        // left 和 right 用于定义当前窗口的大小
        // 注意：这个窗口是从右往左滑动的，和我们之前见过的窗口有点不一样
        int left = 0, right = 0;
        // windowNum 用于维护当前窗口的整数大小
        int windowNum = 0;
        while (right < n) {
            windowNum = windowNum * 10 + (s.charAt(right) - '0');

            // 当前窗口的大小为 k，也就是当前窗口中有 k 个字符了
            if (right - left + 1 == k) {
                ans = Math.max(ans, windowNum);
                // 缩小窗口，这这里需要维护当前窗口的数字大小
                // 也就是把当前窗口对应的数字的最高位减去掉即可
                windowNum -= (s.charAt(left) - '0') * (int)Math.pow(10, k - 1);
                left++;
            }

            right++;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new _3_LargestInteger().largestNum("3215267", 3));
    }
}
