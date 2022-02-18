package com.douma._9_day_哈希查找._136;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _136_single_number {
    /* leetcode 136 号算法题：只出现一次的数字
        给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。
        找出那个只出现了一次的元素。

        输入: [2,2,1]
        输出: 1

        输入: [4,1,2,1,2]
        输出: 4

        你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     */

    // 暴力
    public int singleNumber1(int[] nums) {
        if (nums.length == 1) return nums[0];
        for (int i = 0; i < nums.length; i++) {
            boolean isExist = false;
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[j] == nums[i]) isExist = true;
            }
            if (!isExist) return nums[i];
        }
        return -1;
    }

    // 排序
    public int singleNumber2(int[] nums) {
        if (nums.length == 1) return nums[0];

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 && nums[i] != nums[i + 1]) return nums[i];
            else if (i == nums.length - 1 && nums[i] != nums[i - 1]) return nums[i];
            else if (nums[i] != nums[i + 1] && nums[i] != nums[i - 1]) return nums[i];
        }
        return -1;
    }

    // 哈希查找
    public int singleNumber3(int[] nums) {
        if (nums.length == 1) return nums[0];

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }

        return -1;
    }

    // 位运算
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
