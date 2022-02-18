package com.douma._17_day_二叉树二._222;

import com.douma._17_day_二叉树二.TreeNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _222_count_complete_tree_nodes {
    /* 222. 完全二叉树的节点个数
        给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。

        输入：
             1
           /   \
          2     3
         / \   /
        4   5 6
      输出： 6

      提示：
      1. 树中节点的数目范围是[0, 5 * 10^4]
      2. 0 <= Node.val <= 5 * 10^4
      3. 题目数据保证输入的树是 完全二叉树

      进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
     */

    // DFS 后序遍历
    public int countNodes1(TreeNode root) {
        if (root == null) return 0;

        int left = countNodes1(root.left);
        int right = countNodes1(root.right);

        return left + right + 1;
    }

    // 二分查找
    // 时间复杂度：O((logn)^2)
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        // 计算完全二叉树的最大层数
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }

        // 完全二叉树的节点数的范围是：[2^level, 2^(level + 1) - 1]
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            // 这里需要 + 1
            int mid = low + (high - low + 1) / 2;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    private boolean exists(TreeNode root, int level, int mid) {
        int mask = 1 << (level - 1); // level = 4, 01000
        TreeNode node = root;
        while (node != null && mask > 0) {
            if ((mask & mid) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            mask >>= 1;
        }
        return node != null;
    }
}
