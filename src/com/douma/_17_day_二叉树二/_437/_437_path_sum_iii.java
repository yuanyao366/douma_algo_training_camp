package com.douma._17_day_二叉树二._437;

import com.douma._17_day_二叉树二.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _437_path_sum_iii {
    /* 437. 路径总和 III
    给定一个二叉树，它的每个结点都存放着一个整数值。
    找出路径和等于给定数值的路径总数。

    路径不需要从根节点开始，也不需要在叶子节点结束，
    但是路径方向必须是向下的（只能从父节点到子节点）。

    二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

    root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

          10
         /  \
        5   -3
       / \    \
      3   2   11
     / \   \
    3  -2   1

    返回 3。和等于 8 的路径有:

    1.  5 -> 3
    2.  5 -> 2 -> 1
    3.  -3 -> 11
     */

    // 计算每个节点所有路径和
    // O(nlogn)
    public int pathSum1(TreeNode root, int sum) {
        return dfs(root, new ArrayList<>(), sum);
    }

    private int dfs(TreeNode node, List<Integer> parentPathSumList, int targetSum) {
        if (node == null) return 0;

        int cnt = 0;
        List<Integer> tmp = new ArrayList<>();
        // O(logn)
        for (int i = 0; i < parentPathSumList.size(); i++) {
            int num = parentPathSumList.get(i) + node.val;
            tmp.add(num);
            if (num == targetSum) cnt++;
        }
        tmp.add(node.val);
        if (node.val == targetSum) cnt++;

        int leftCnt = dfs(node.left, tmp, targetSum);
        int rightCnt = dfs(node.right, tmp, targetSum);

        return cnt + leftCnt + rightCnt;
    }

    // 在一个数组中求连续子数组【区间】之和等于 targetSum 的连续子数组的个数
    public int pathSum(int[] nums, int targetSum) {
        int ans = 0;
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        int currSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            ans += prefixSumMap.getOrDefault(currSum - targetSum, 0);
            prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);
        }
        return ans;
    }

    // DFS(前序遍历) + 前缀和
    // O(n)
    private int res = 0;
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        dfs1(root, 0, sum, prefixSumMap);
        return res;
    }

    private void dfs1(TreeNode node,
                      int currSum, int targetSum,
                      Map<Integer, Integer> prefixSumMap) {
        if (node == null) return;

        currSum += node.val;
        res +=prefixSumMap.getOrDefault(currSum - targetSum, 0);
        prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);

        dfs1(node.left, currSum, targetSum, prefixSumMap);
        dfs1(node.right, currSum, targetSum, prefixSumMap);

        prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1);
    }

    // 前缀和 + DFS（后序遍历）
    public int pathSum3(TreeNode root, int sum) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int currSum = 0;
        prefixSumMap.put(0, 1);
        return dfs2(root, currSum, sum, prefixSumMap);
    }

    private int dfs2(TreeNode node,
                     int currSum, int target,
                     Map<Integer, Integer> prefixSumMap) {
        if (node == null) return 0;

        currSum += node.val;
        int res = prefixSumMap.getOrDefault(currSum - target, 0);
        prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);

        res += dfs2(node.left, currSum, target, prefixSumMap);
        res += dfs2(node.right, currSum, target, prefixSumMap);

        prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1);

        return res;
    }
}
