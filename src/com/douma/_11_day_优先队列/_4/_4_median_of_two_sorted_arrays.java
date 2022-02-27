package com.douma._11_day_优先队列._4;

import java.util.PriorityQueue;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _4_median_of_two_sorted_arrays {
    /* leetcode 4 号算法题：寻找两个正序数组的中位数

    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
    请你找出并返回这两个正序数组的 中位数 。

    输入：nums1 = [1,3], nums2 = [2]
    输出：2.00000
    解释：合并数组 = [1,2,3] ，中位数 2

    输入：nums1 = [1,2], nums2 = [3,4]
    输出：2.50000
    解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

    1. nums1.length == m
    2. nums2.length == n
    3. 0 <= m <= 1000、0 <= n <= 1000、1 <= m + n <= 2000
    4. -10^6 <= nums1[i], nums2[i] <= 10^6
    进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
     */

    // 大顶堆用于存储较小的一半元素
    private PriorityQueue<Integer> maxHeap;
    // 小顶堆用于存储较大的一半元素
    private PriorityQueue<Integer> minHeap;

    // 大顶堆 + 小顶堆
    // 时间复杂度：O(mlogm + nlog(m + n))
    // 空间复杂度：O(m + n)
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>();
        // O(mlogm)
        for (int num : nums1) addNum(num);
        // O(nlog(m + n))
        for (int num : nums2) addNum(num);

        if (maxHeap.size() > minHeap.size()) {
            // 说明有奇数个元素，那么大顶堆堆顶元素就是中位数
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) * 0.5;
        }
    }

    // 从数据流中添加一个整数到数据结构中。
    // 时间复杂度：log(n)
    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }

        if (num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.remove());
        }
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.remove());
        }
    }

    // 合并排序
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(m + n)
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] tmp = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                tmp[k++] = nums1[i++];
            } else {
                tmp[k++] = nums2[j++];
            }
        }

        while (i < m) tmp[k++] = nums1[i++];

        while (j < n) tmp[k++] = nums2[j++];
        if ((m + n) % 2 == 1) {
            return tmp[(m + n) / 2];
        } else {
            return (tmp[(m + n) / 2] + tmp[(m + n - 1) / 2]) / 2.0;
        }
    }

    // 合并排序
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(1)
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int len = m + n;

        int lower = -1, upper = -1;
        int start1 = 0, start2 = 0;
        for (int i = 0; i <= len / 2; i++) {
            lower = upper;
            if (start1 < m && (start2 >= n || nums1[start1] < nums2[start2])) {
                upper = nums1[start1++];
            } else {
                upper = nums2[start2++];
            }
        }
        return len % 2 == 0 ? (lower + upper) / 2.0 : upper;
    }

    // 二分查找
    // 时间复杂度：O(log(m + n))
    // 空间复杂度：O(l)
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int m = nums1.length; // 5
        int n = nums2.length; // 10
        int leftK = (m + n + 1) / 2; // 8
        int rightK = (m + n + 2) / 2; // 8
        // 将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        int lower = getKth(nums1, nums2, leftK);
        int upper = getKth(nums1, nums2, rightK);
        return (lower + upper) * 0.5;
    }

    private int getKth(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int i = 0, j = 0;
        while (true) {
            if (i == len1) return nums2[j + k - 1];
            if (j == len2) return nums1[i + k - 1];
            if (k == 1) return Math.min(nums1[i], nums2[j]);
            // tips：计算 newi 和 newj 需要减 1 的原因：k/2 表示的是长度，长度是从 1 开始，而下标是从 0 开始的，所以需要减 1
            int newi = Math.min(i + (k / 2), len1) - 1;
            int newj = Math.min(j + (k / 2), len2) - 1;
            // tips：而下面计算 i 和 j 的时候加 1 的原因是：就是排除 i 前面或者 j 前面的元素，所以往前走一个
            // 计算 k 的时候加 1 的原因是：用从 0 开始的下标计算从 1 开始的长度，都需要加 1 的
            if (nums1[newi] < nums2[newj]) {
                k = k - (newi - i + 1);
                i = newi + 1;
            } else {
                k = k - (newj - j + 1);
                j = newj + 1;
            }
        }
    }

    // 划分数组
    // 时间复杂度：O(log(m + n))
    // 空间复杂度：O(l)
    public double findMedianSortedArrays5(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > n) {
            int[] temp = nums1; nums1 = nums2; nums2 = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = iMin + (iMax - iMin) / 2;
            int j = halfLen - i;
            if (i < iMax && nums2[j - 1] > nums1[i]) {
                iMin = i + 1;
            } else if (i > iMin && nums1[i - 1] > nums2[j]) {
                iMax = i - 1;
            } else {
                // B[j - 1] <= A[i] && A[i - 1] <= B[j]
                int maxLeft;
                if (i == 0) maxLeft = nums2[j - 1];
                else if (j == 0) maxLeft = nums1[i - 1];
                else maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                if ((m + n) % 2 == 1) return maxLeft;

                int minRight;
                if (i == m) minRight = nums2[j];
                else if (j == n) minRight = nums1[i];
                else minRight = Math.min(nums1[i], nums2[j]);
                return (maxLeft + minRight) * 0.5;
            }
        }
        return 0.0;
    }

}
