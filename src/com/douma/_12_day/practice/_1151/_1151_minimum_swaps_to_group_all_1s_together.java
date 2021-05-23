package com.douma._12_day.practice._1151;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1151_minimum_swaps_to_group_all_1s_together {
    /* leetcode 1151. 最少交换次数来组合所有的 1
    给出一个二进制数组 data，你需要通过交换位置，
    将数组中 任何位置 上的 1 组合到一起，并返回所有可能中所需 最少的交换次数。

    示例 1：
    输入：[1,0,1,0,1]
    输出：1
    解释：
    有三种可能的方法可以把所有的 1 组合在一起：
    [1,1,1,0,0]，交换 1 次；
    [0,1,1,1,0]，交换 2 次；
    [0,0,1,1,1]，交换 1 次。
    所以最少的交换次数为 1。

    示例 2：
    输入：[0,0,0,1,0]
    输出：0
    解释：
    由于数组中只有一个 1，所以不需要交换。

    示例 3：
    输入：[1,0,1,0,1,0,0,1,1,0,1]
    输出：3
    解释：
    交换 3 次，一种可行的只用 3 次交换的解决方案是 [0,0,0,0,0,1,1,1,1,1,1]。
     
    提示：
    1 <= data.length <= 10^5
    0 <= data[i] <= 1

     */

    public int minSwaps(int[] data) {
        // 1. 统计数组中元素值等于 1 的个数
        int k = 0;
        for (int x : data) {
            if (x == 1) k++;
        }

        // 2. 用一个大小为 k 的窗口从左往右扫描，窗口中元素 1 最多的窗口就是我们要找的
        // 存储所有 k 大小窗口中最多的 1 的数量
        int maxOneCounts = 0;
        // 存储每个窗口中 1 的数量
        int windowOneCounts = 0;
        // 维护滑动窗口
        int left = 0;
        int right = 0;
        while (right < data.length) {
            if (data[right] == 1) windowOneCounts++;
            // left 移动时机：窗口大小等于 k
            // left 移动策略：
            // 1. 比较当前窗口的 1 的数量和之前最大的 1 的数量，更新最大 1 的数量
            // 2. 移动 left 之前先判断 left 是不是指向 1，如果是的话，当前窗口中 1 的数量需要减掉1
            if (right - left + 1 == k) {
                if (windowOneCounts > maxOneCounts) maxOneCounts = windowOneCounts;
                if (data[left] == 1) windowOneCounts--;
                left++;
            }
            right++;
        }
        //
        return k - maxOneCounts;
    }
}
