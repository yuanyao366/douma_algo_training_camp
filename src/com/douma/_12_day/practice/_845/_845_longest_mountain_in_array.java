package com.douma._12_day.practice._845;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _845_longest_mountain_in_array {
    /* leetcode 845. 数组中的最长山脉
    我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：

    B.length >= 3
    存在 0 < i < B.length - 1
    使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
    （注意：B 可以是 A 的任意子数组，包括整个数组 A。）

    给出一个整数数组 A，返回最长 “山脉” 的长度。

    如果不含有 “山脉” 则返回 0。

    示例 1：
    输入：[2,1,4,7,3,2,5]
    输出：5
    解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。

    示例 2：
    输入：[2,2,2]
    输出：0
    解释：不含 “山脉”。
     
    提示：
    0 <= A.length <= 10000
    0 <= A[i] <= 10000

     */

    public int longestMountain(int[] arr) {
        int n = arr.length;
        int ans = 0;
        int left = 0;
        while (left + 2 < n) {
            int right = left + 1;
            if (arr[left] < arr[left + 1]) {
                // 找到最高点
                while (right + 1 < n && arr[right] < arr[right + 1]) {
                    right++;
                }

                if (right + 1 < n && arr[right] > arr[right + 1]) { // 有更低的点
                    // 找到最低点
                    while (right + 1 < n && arr[right] > arr[right + 1]) {
                        right++;
                    }
                    ans = Math.max(ans, right - left + 1);
                } else { // 没有更低的点了
                    right++;
                }
            }
            left = right;
        }
        return ans;
    }
}
