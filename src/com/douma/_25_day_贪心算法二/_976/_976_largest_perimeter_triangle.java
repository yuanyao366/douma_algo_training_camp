package com.douma._25_day_贪心算法二._976;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _976_largest_perimeter_triangle {
    /* 976. 三角形的最大周长
    给定由一些正数（代表长度）组成的数组 A，
    返回由其中三个长度组成的、面积不为零的三角形的最大周长。

    如果不能形成任何面积不为零的三角形，返回 0。

    示例 1：
    输入：[2,1,2]
    输出：5

    示例 2：
    输入：[1,2,1]
    输出：0

    示例 3：
    输入：[3,2,3,4]
    输出：10

    示例 4：
    输入：[3,6,2,3]
    输出：8
     
    提示：
    3 <= A.length <= 10000
    1 <= A[i] <= 10^6
     */

    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) { // bug 修复：是 i--
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }
        return 0;
    }
}
