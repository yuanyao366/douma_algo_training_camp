package com.douma._22_day_回溯算法一._113;

import com.douma._17_day_二叉树二.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _113_PathSum_Final_Code {
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, target, new ArrayList<>(), res);
        return res;
    }

    private void dfs(TreeNode node, int targetSum,
                     List<Integer> path, List<List<Integer>> res) {
        if (node == null) return;
        path.add(node.val);
        int currNodeTarget = targetSum - node.val;
        if (node.left == null && node.right == null && currNodeTarget == 0) {
            // 添加路径的时候需要 new 一个新的 ArrayList 的原因：
            // 1. 使得 res 中的对象和 path 不是同一个对象
            res.add(new ArrayList<>(path));
        }
        dfs(node.left, currNodeTarget, path, res);
        dfs(node.right, currNodeTarget, path, res);
        // 回溯的过程中，将当前的节点从 path 中删除
        path.remove(path.size() - 1);
    }
}
