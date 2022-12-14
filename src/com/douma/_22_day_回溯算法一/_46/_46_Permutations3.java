package com.douma._22_day_回溯算法一._46;

import java.util.ArrayList;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _46_Permutations3 {
    // O(n! * n^2)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(nums, path, res);
        return res;
    }

    private void dfs(int[] nums,
                     List<Integer> path,
                     List<List<Integer>> res) { // O(n^2)
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 剪枝，判断重复使用的数字
            if (path.contains(nums[i])) continue;
            path.add(nums[i]);
            dfs(nums, path, res);
            // 回溯的过程中，将当前的节点从 path 中删除
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _46_Permutations3().permute(new int[]{1, 2, 3}));
    }
}
