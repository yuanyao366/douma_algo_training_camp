package com.douma._13_day_综合应用一._229;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _229_majority_element_ii {
    /*  229. 求众数 II
    给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。

    进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。

    输入：[3,2,3]
    输出：[3]

    输入：[1,1,1,3,3,2,2,2]
    输出：[1,2]

    1 <= nums.length <= 5 * 10^4
    -10^9 <= nums[i] <= 10^9
     */

    // Boyer-Moore 投票算法
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public List<Integer> majorityElement1(int[] nums) {
        int candidate1 = 0, candidate2 = 0;
        int count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1++;
            } else if (count2 == 0) {
                candidate2 = num;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }
        List<Integer> res = new ArrayList<>();
        if (count1 > nums.length / 3) res.add(candidate1);
        if (count2 > nums.length / 3) res.add(candidate2);
        return res;
    }

    // 哈希查找
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (int key : countMap.keySet()) {
            if (countMap.get(key) > nums.length / 3) {
                res.add(key);
            }
        }
        return res;
    }
}
