package com.douma._13_day_综合应用一._15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15_ThreeSum3 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3)
            return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums); // O(nlogn)

        // O(n^2)
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 在 i + 1 ... nums.length - 1 中查找相加等于 -nums[i] 的两个数
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    /*
                        下面的代码相当于：
                        while (left < right) {
                            // 不管前后相不相等，left 都要往前走
                            left++;
                            if (nums[left - 1] != nums[left]) break;
                        }
                        while (left < right) {
                            // 不管前后相不相等，right 都要往后走
                            right--;
                            if (nums[right + 1] != nums[right]) break;
                        }
                     */
                    // 去重
                    while (left < right && nums[left] == nums[++left]);
                    while (left < right && nums[right] == nums[--right]);
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(new _15_ThreeSum3().threeSum(nums));
    }
}
