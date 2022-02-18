package com.douma._30_day_动态规划总结._673;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _673_number_of_longest_increasing_subsequence {
    /* 673. 最长递增子序列的个数
    给定一个未排序的整数数组，找到最长递增子序列的个数。

    示例 1:
    输入: [1,3,5,4,7]
    输出: 2
    解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。

    示例 2:
    输入: [2,2,2,2,2]
    输出: 5
    解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。

    注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
     */

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;
        // 1. 状态(双状态)数组的定义
        // lengths[i] 表示在子区间 [0...i] 且以 nums[i] 结尾的最长递增子序列的长度
        int[] lengths = new int[n];
        // counts[i] 表示在子区间 [0...i] 且以 nums[i] 结尾的最长递增子序列的个数
        int[] counts = new int[n];

        // 2. 状态初始化
        // 每个元素都可以以自身为一个长度的子序列，所以 lengths 初始化为 1
        Arrays.fill(lengths, 1);
        // 因为每个元素可以以自身结尾的最长子序列的情况至少有一种，所以 counts 初始化为 1
        Arrays.fill(counts, 1);

        // 3. 状态转移
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 需要符合递增条件
                if (nums[j] < nums[i]) {
                    // 以 nums[j] 结尾的最长递增序列长度 >= 以 nums[i] 结尾的最长递增序列长度
                    if (lengths[j] >= lengths[i]) {
                        // 更新以 nums[i] 结尾的最长递增序列的长度和个数
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    } else if (lengths[j] + 1 == lengths[i]) {
                        counts[i] += counts[j];
                    }
                }
            }
        }

        // 求最长递增序列的长度
        int longest = 0;
        for (int len : lengths) longest = Math.max(longest, len);

        // 求最长递增序列的个数
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (lengths[i] == longest) {
                ans += counts[i];
            }
        }

        return ans;
    }
}
