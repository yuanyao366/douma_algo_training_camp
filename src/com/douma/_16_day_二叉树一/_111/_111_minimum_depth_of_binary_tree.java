package com.douma._16_day_二叉树一._111;

import com.douma._16_day_二叉树一.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _111_minimum_depth_of_binary_tree {
    /* 111. 二叉树的最小深度
        给定一个二叉树，找出其最小深度。
        最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
        说明：叶子节点是指没有子节点的节点。

        输入：
              1
             / \
            2   3
           / \
          4   5

         输出：2

        输入：
              1
             / \
            2  NULL
           /
          4
         /
        5
       /
      6
         输出： 5
     */

    // 层序遍历(BFS)
    public int minDepth1(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levels = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            levels++;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left == null && curr.right == null) {
                    return levels;
                }
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return levels;
    }

    private class Node {
        TreeNode node;
        int depth;

        Node(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    // DFS 迭代 - 前序遍历
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;

        // bug 修复：这里需要初始化为最大值
        int res = Integer.MAX_VALUE;
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(root, 1));
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            TreeNode curr = node.node;
            int currDepth = node.depth;

            if (curr.left == null && curr.right == null) {
                res = Math.min(res, currDepth);
            }

            if (curr.right != null) {
                stack.push(new Node(curr.right, currDepth + 1));
            }

            if (curr.left != null) {
                stack.push(new Node(curr.left, currDepth + 1));
            }
        }

        return res;
    }

    // DFS 递归 - 前序遍历
    private int res = Integer.MAX_VALUE;
    public int minDepth3(TreeNode root) {
        // bug 修复：需要加上特判，要不然会返回最大值
        if (root == null) return 0;
        preorder(root, 1);
        return res;
    }

    private void preorder(TreeNode node, int depth) {
        if (node == null) return;
        // 处理当前的节点
        if (node.left == null && node.right == null) {
            res = Math.min(res, depth);
        }

        preorder(node.left, depth + 1);
        preorder(node.right, depth + 1);
    }

    // DFS 递归 - 后序遍历
    // 计算以 root 为根节点的二叉树的最小深度
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int leftMinDepth = minDepth(root.left);
        int rightMinDepth = minDepth(root.right);

        if (root.left == null) {
            return rightMinDepth + 1;
        } else if (root.right == null) {
            return leftMinDepth + 1;
        } else {
            return Math.min(leftMinDepth, rightMinDepth) + 1;
        }
    }
}
