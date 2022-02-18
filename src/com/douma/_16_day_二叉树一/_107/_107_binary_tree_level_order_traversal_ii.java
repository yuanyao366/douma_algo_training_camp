package com.douma._16_day_二叉树一._107;

import com.douma._16_day_二叉树一.TreeNode;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _107_binary_tree_level_order_traversal_ii {
    /* 107. 二叉树的层序遍历 II
        给定一个二叉树，返回其节点值自底向上的层序遍历。
        （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

        输入：
            3
           / \
          9  20
            /  \
           15   7
        输出：
        [
            [15, 7],
            [9, 20],
            [3]
        ]
     */

    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一层的节点
            int size = queue.size();
            List<Integer> levelNodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                levelNodes.add(curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            res.add(levelNodes);
        }
        // 反转
        Collections.reverse(res);
        return res;
    }

    // 前序遍历(递归)实现层序遍历
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        preorder(root, 0, res);
        Collections.reverse(res);
        return res;
    }

    private void preorder(TreeNode node, int currLevel, List<List<Integer>> res) {
        if (node == null) return;

        // 处理当前遍历的节点
        if (res.size() == currLevel) {
            List<Integer> levelNodes = new ArrayList<>();
            levelNodes.add(node.val);
            res.add(levelNodes);
        } else {
            res.get(currLevel).add(node.val);
        }

        preorder(node.left, currLevel + 1, res);
        preorder(node.right, currLevel + 1, res);
    }
}
