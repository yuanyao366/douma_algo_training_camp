package com.douma._9_day_哈希查找._128;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _128_longest_consecutive_sequence {
    /*  leetcode 128 号算法题：最长连续序列

        给定一个未排序的整数数组 nums ，
        找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

        输入：nums = [100,4,200,1,3,2]
        输出：4

        输入：nums = [0,3,7,2,5,8,4,6,0,1]
        输出：9

        0 <= nums.length <= 10^4
        -10^9 <= nums[i] <= 10^9

        进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
     */

    // 排序解法
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(n)
    public int longestConsecutive1(int[] nums) {
        if (nums.length < 2) return nums.length;

        Arrays.sort(nums);

        int ans = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) continue;

            if (nums[i] - nums[i - 1] == 1) {
                count++;
            } else {
                ans = Math.max(ans, count);
                count = 1;
            }
        }

        return Math.max(ans, count);
    }

    // 哈希查找解法
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int longestConsecutive(int[] nums) {
        if (nums.length < 2) return nums.length;

        Set<Integer> set = new HashSet<>();
        for (Integer num : nums) set.add(num);

        int ans = 1;
        for (int num : nums) {
            // 这里会存在重复计算，为什么会产生以及如何解决，请参考 issue：
            // https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4H4RZ
            if (set.contains(num - 1)) continue;

            int currNum = num;
            int count = 1;
            while (set.contains(currNum + 1)) {
                currNum += 1;
                count += 1;
            }

            ans = Math.max(ans, count);
        }

        return ans;
    }
}
