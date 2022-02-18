package com.douma._13_day_综合应用一._169;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _169_majority_element {
    /* 169. 多数元素(求众数)
    给定一个大小为 n 的数组，找到其中的多数元素。
    多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

    你可以假设数组是非空的，并且给定的数组总是存在多数元素。

    输入：[3,2,3]
    输出：3

    输入：[2,2,1,1,1,2,2]
    输出：2

    进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
     */

    // 哈希查找
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int cnt = map.getOrDefault(num, 0) + 1;
            if (cnt > nums.length / 2) return num;
            map.put(num, cnt);
        }
        return -1;
    }

    // 排序查找
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(logn) 或者 O(n)
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // 堆查找
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(n)
    public int majorityElement3(int[] nums) {
        int k = nums.length / 2 + 1;
        // 查找第 k 小元素
        // 大顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> b - a);
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) pq.remove();
        }

        return pq.peek();
    }

    // 快速排序分区优化
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    private Random random = new Random(System.currentTimeMillis());

    public int majorityElement4(int[] nums) {
        int k = nums.length / 2 + 1;
        // 查找第 k 小元素
        int left = 0, right = nums.length - 1;
        int target = nums.length - k;
        while (true) {
            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == target) {
                return nums[pivotIndex];
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
    }

    private int partition(int[] nums, int left, int right) {
        if (right > left) {
            int pivotIndex = left + 1 + random.nextInt(right - left);
            swap(nums, pivotIndex, right);
        }
        int pivot = nums[right];
        int less = left, great = left;
        for (; great < right; great++) {
            if (nums[great] < pivot) {
                swap(nums, less, great);
                less++;
            }
        }
        swap(nums, less, right);
        return less;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 分治
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(logn)
    public int majorityElement5(int[] nums) {
        return majorityElement(nums, 0, nums.length - 1);
    }

    private int majorityElement(int[] nums, int lo, int hi) {
        if (lo == hi) return nums[lo];

        int mid = lo + (hi - lo) / 2;
        int leftNum = majorityElement(nums, lo, mid);
        int rightNum = majorityElement(nums, mid + 1, hi);

        if (leftNum == rightNum) return leftNum;

        int leftNumCnt = countInRange(nums, leftNum, lo, hi);
        int rightNumCnt = countInRange(nums, rightNum, lo, hi);
        return leftNumCnt > rightNumCnt ? leftNum : rightNum;
    }

    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    // Boyer-Moore 投票算法
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int majorityElement(int[] nums) {
        int candidate = -1;
        int count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            } else if (count == 0) {
                candidate = num;
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }
}
