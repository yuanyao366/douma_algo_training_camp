package com.douma._17_day_二叉树二._257;

import com.douma._17_day_二叉树二.TreeNode;

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
public class _257_binary_tree_paths {
    /* 257. 二叉树的所有路径
    给定一个二叉树，返回所有从根节点到叶子节点的路径。

    说明: 叶子节点是指没有子节点的节点。

    输入:
       1
     /   \
    2     3
     \
      5
    输出: [“1->2->5”, "1->3"]
    输出: [
            [1,2,5],
            [1,3]
        ]
     */

    // DFS
    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, "", res);
        return res;
    }

    private void dfs(TreeNode node, String parentPath, List<String> res) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            res.add(parentPath + node.val);
            return;
        }

        dfs(node.left, parentPath + node.val + "->", res);
        dfs(node.right, parentPath + node.val + "->", res);
    }

    // BFS
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        if (root == null) {
            return paths;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<String> pathQueue = new LinkedList<String>();

        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val));

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();

            if (node.left == null && node.right == null) {
                paths.add(path);
                continue;
            }

            if (node.left != null) {
                nodeQueue.offer(node.left);
                pathQueue.offer(new StringBuilder(path).append("->").append(node.left.val).toString());
            }

            if (node.right != null) {
                nodeQueue.offer(node.right);
                pathQueue.offer(new StringBuilder(path).append("->").append(node.right.val).toString());
            }
        }
        return paths;
    }
}
