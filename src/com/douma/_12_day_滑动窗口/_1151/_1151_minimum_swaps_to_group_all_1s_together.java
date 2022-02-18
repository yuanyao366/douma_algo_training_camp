package com.douma._12_day_滑动窗口._1151;

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

        // 维护窗口大小为 k 的滑动窗口
        int left = 0, right = 0;
        // 存储每个窗口中 0 的数量
        int windowZeroCnt = 0;
        // 所有窗口中最少的 0 的数量
        int minZeroCnt = Integer.MAX_VALUE;
        while (right < data.length) {
            if (data[right] == 0) windowZeroCnt++;
            if (right - left + 1 == k) {
                minZeroCnt = Math.min(minZeroCnt, windowZeroCnt);
                if (data[left] == 0) windowZeroCnt--;
                left++;
            }
            right++;
        }
        return minZeroCnt == Integer.MAX_VALUE ? 0 : minZeroCnt;
    }

}
