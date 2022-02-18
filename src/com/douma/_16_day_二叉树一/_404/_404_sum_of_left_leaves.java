package com.douma._16_day_二叉树一._404;

import com.douma._16_day_二叉树一.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _404_sum_of_left_leaves {
    /* 404. 左叶子之和
       计算给定二叉树的所有左叶子之和。

        示例：

            3
           / \
          9  20
            /  \
           15   7

        输出：24
     */

    // DFS 前序遍历
    private int sum = 0;
    public int sumOfLeftLeaves1(TreeNode root) {
        preorder(root, root);
        return sum;
    }

    private void preorder(TreeNode node, TreeNode parent) {
        if (node == null) return;

        // 处理当前节点
        if (node.left == null &&
                node.right == null &&
                parent.left == node) {
            sum += node.val;
        }

        preorder(node.left, node);
        preorder(node.right, node);
    }

    // 需要注意的是：我们完全也可以使用后序遍历的方式来实现计算每一个左叶子节点
    // 方法一：不返回值
    // 后序遍历返回所有左叶子之和
    private void postorder1(TreeNode node, TreeNode parent) {
        if (node == null) return;

        postorder1(node.left, node);
        postorder1(node.right, node);

        // 处理当前节点
        if (node.left == null &&
                node.right == null &&
                parent.left == node) {
            sum += node.val;
        }
    }

    // DFS 后序遍历
    public int sumOfLeftLeaves2(TreeNode root) {
        return postorder(root, root);
    }

    // 方法二：返回值
    // 先计算左右子节点的值，再返回根节点的值，本质就是后序遍历
    private int postorder(TreeNode node, TreeNode parent) {
        if (node == null) return 0;
        if (node.left == null &&
                node.right == null &&
                parent.left == node) {
            return node.val;
        }

        int leftLeavesSum = postorder(node.left, node);
        int rightLeavesSum = postorder(node.right, node);

        // 处理当前根节点
        return leftLeavesSum + rightLeavesSum;
    }

    // BFS
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                if (isLeafNode(node.left)) {
                    sum += node.left.val;
                } else {
                    queue.offer(node.left);
                }
            }
            if (node.right != null && !isLeafNode(node.right))
                queue.offer(node.right);
        }
        return sum;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
