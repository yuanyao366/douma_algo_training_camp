package com.douma.笔试代码.meituan._20220316;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class sub_array {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4, 5, 6};

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = i; j < nums.length; j++) {
                list.add(nums[j]);
                if ((j - i + 1) % 2 == 1) {
                    res.add(new ArrayList<>(list));
                }
            }
        }

        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }
}
