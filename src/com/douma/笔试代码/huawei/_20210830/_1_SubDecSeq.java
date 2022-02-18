package com.douma.笔试代码.huawei._20210830;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _1_SubDecSeq {
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(n)
    public static void main(String[] args) {
        // 1. 输入数据的处理，转成 int 类型的数组
        Scanner scanner = new Scanner(System.in);
        String[] data = scanner.nextLine().split(",");
        int n = data.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(data[i]);
        }

        // 2. 求符合条件的所有子序列
        List<List<Integer>> res = new ArrayList<>();
        // visited 用于标记对应索引的元素是否已经被划分到一个递减子序列中
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n && !visited[i]; i++) {
            List<Integer> subSeq = new ArrayList<>();
            subSeq.add(nums[i]);
            visited[i] = true;
            int prevNum = nums[i];
            for (int j = i + 1; j < n && !visited[i]; j++) {
                if (nums[j] < prevNum) {
                    subSeq.add(nums[j]);
                    visited[j] = true;
                    prevNum = nums[j];
                }
            }
            res.add(subSeq);
        }

        System.out.println(res);
    }
}
