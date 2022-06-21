package com.douma._9_day_哈希查找._1122;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1122_relative_sort_array {
    /* leetcode 1122 号算法题：数组的相对排序
    给你两个数组，arr1 和 arr2，
        1. arr2 中的元素各不相同
        2. arr2 中的每个元素都出现在 arr1 中

    对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
    未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

    输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19,8], arr2 = [2,1,4,3,9,6]
    输出：[2,2,2,1,4,3,3,9,6,7,8,19]

    - 1 <= arr1.length, arr2.length <= 1000
    - 0 <= arr1[i], arr2[i] <= 1000
    - arr2 中的元素 arr2[i] 各不相同
    - arr2 中的每个元素 arr2[i] 都出现在 arr1 中
     */

    // 计数排序
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[1001];
        for (int num : arr1) count[num]++;

        int index = 0;
        for (int num : arr2) {
            for (int i = 0; i < count[num]; i++) {
                arr1[index++] = num;
            }
            // 清 0 的原因：
            // 在 arr1 中等于 num 的所有的元素都放在一起了
            // 也就是 num 排好序了，为了可以区分出已经排好序，和还没排序的元素
            // 我们将排好序的元素出现的次数清 0
            count[num] = 0;
            // 清 0 后，在下面的循环中就不用处理了，下面的循环只要处理在 arr2 中没出现的元素了
        }

        for (int num = 0; num < 1001; num++) {
            for (int i = 0; i < count[num]; i++) {
                arr1[index++] = num;
            }
        }

        return arr1;
    }

    // 自定义排序
    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }

        List<Integer> list = new ArrayList<>();
        for (int num : arr1) list.add(num);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                if (map.containsKey(x)) {
                    // 如果 x 和 y 都出现在两个数组中，那么返回 x 和 y 在 arr2 索引的差
                    //      如果差等于 0，那么 x 和 y 在 arr1 的顺序不变
                    //      如果差等于负数，那么说明在 arr2 中， x 在 y 的前面，那么在 arr1 中， x 和 y 就升序排列(即 x 在 y 前面)
                    //      如果差等于正数，那么说明在 arr2 中， x 在 y 的后面，那么在 arr1 中， x 和 y 就降序排列(即 y 在 x 的前面)
                    // 如果 x 在 arr2 中，但是 y 不在 arr2 中，那么返回负数 (-1)，那么 x 和 y 在 arr1 中升序排列(即 x 在 y 前面)
                    return map.containsKey(y) ? map.get(x) - map.get(y) : -1;
                } else {
                    // 如果 x 不在 arr2 中，但是 y 在 arr2 中，那么返回正数(即 1)，那么 x 和 y 在 arr1 中降序排列(即 y 在 x 的前面)
                    // 如果 x 和 y 都在 arr2 中，那么 x 和 y 就按照 x 和 y 的大小进行升序排列
                    return map.containsKey(y) ? 1 : x - y;
                }
            }
        });

        for (int i = 0; i < arr1.length; i++) arr1[i] = list.get(i);
        return arr1;
    }
}
