package com.douma._9_day._1122;

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
            count[num] = 0;
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
                    return map.containsKey(y) ? map.get(x) - map.get(y) : -1;
                } else {
                    return map.containsKey(y) ? 1 : x - y;
                }
            }
        });

        for (int i = 0; i < arr1.length; i++) arr1[i] = list.get(i);
        return arr1;
    }
}
