package com.douma._21_day_综合应用二._220;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _220_contains_duplicate_iii {
    /* 220. 存在重复元素 III
        给你一个整数数组 nums 和两个整数 k 和 t 。
        请你判断是否存在 两个不同下标 i 和 j，
        使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。

        如果存在则返回 true，不存在返回 false。

        示例 1：
        输入：nums = [1,2,3,1], k = 3, t = 0
        输出：true

        示例 2：
        输入：nums = [1,0,1,1], k = 1, t = 2
        输出：true

        示例 3：
        输入：nums = [1,5,9,1,5,9], k = 2, t = 3
        输出：false
     */

    /*
    nums：[2147483647,-1,2147483647]
    k = 1
    t = 2147483647

    注意越界
    */
    // 滑动窗口 + 有序集合
    // 时间复杂度：O(nlog(min(n, k)))
    // 空间复杂度：O(min(n, k))
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        int left = 0;
        int right = 0;

        TreeSet<Long> window = new TreeSet<>();

        while (right < nums.length) {
            // 窗口内区间查找
            long x = nums[right];
            // 找到大于等于 x - t 的最小的元素 y，确定 y - x <= t
            Long y = window.ceiling(x - (long)t);
            // 如果 y 存在，并且 y <= x + t，就可以确定  x - y <= t
            if (y != null && y <= x + t) {
                return true;
            }

            window.add((long)nums[right]);

            if (window.size() > k) {
                // bug 修复：需要强转成 long 类型
                window.remove((long)nums[left]);
                left++;
            }

            right++;
        }

        return false;
    }

    // 滑动窗口 + 桶
    // 时间复杂度：O(n)
    // 空间复杂度：O(min(n, k))
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int left = 0;
        int right = 0;

        long bucketSize = t + 1L;
        // key 是 bucket_id
        // value 对应桶中的元素值
        // bug 修复：桶内存储的元素值也需要是 long 类型的
        // 要不然下面的 x - window.get(leftBucketId) <= t 会越界
        Map<Long, Long> window = new HashMap<>();

        while (right < nums.length) {
            // 窗口内区间查找
            long x = nums[right];
            long bucketId = getBucketId(x, bucketSize);

            // 一个桶中有多于 1 个元素值存在
            if (window.containsKey(bucketId)) return true;
            // 判断相邻的桶中是否存在符合条件的原始值
            long leftBucketId = bucketId - 1, rightBucketId = bucketId + 1;
            if (window.containsKey(leftBucketId)
                    && x - window.get(leftBucketId) <= t) return true;
            if (window.containsKey(rightBucketId)
                    && window.get(rightBucketId) - x <= t) return true;

            window.put(bucketId, x);

            // 维护桶的个数，桶的个数最多为 k 个，超过 k 个，我们就删除左边的桶
            if (window.size() > k) {
                window.remove(getBucketId(nums[left], bucketSize));
                left++;
            }

            right++;
        }

        return false;
    }

    private long getBucketId(long x, long bucketSize) {
        if (x >= 0)
            return x / bucketSize;
        return (x + 1) / bucketSize - 1;
    }
}
