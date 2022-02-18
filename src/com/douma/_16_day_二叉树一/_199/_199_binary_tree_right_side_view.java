package com.douma._16_day_二叉树一._199;

import com.douma._16_day_二叉树一.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _199_binary_tree_right_side_view {
    /* 199. 二叉树的右视图
    给定一棵二叉树，想象自己站在它的右侧，
    按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

       1            <---
     /   \
    2     3         <---
     \
      5            <---
    输出：[1,3,5]
     */

    // BFS
    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一层的节点
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (i == size - 1) res.add(curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return res;
    }

    // DFS
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        preorder(root, 0, res);
        return res;
    }

    private void preorder(TreeNode node, int currLevel, List<Integer> res) {
        if (node == null) return;

        // 处理当前遍历的节点
        if (res.size() == currLevel) {
            res.add(node.val);
        }

        preorder(node.right, currLevel + 1, res);
        preorder(node.left, currLevel + 1, res);
    }
}
