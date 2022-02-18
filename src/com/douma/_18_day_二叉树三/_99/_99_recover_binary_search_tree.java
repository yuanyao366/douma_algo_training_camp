package com.douma._18_day_二叉树三._99;

import com.douma._18_day_二叉树三.TreeNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _99_recover_binary_search_tree {
    /* 99. 恢复二叉搜索树
    给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。
    请在不改变其结构的情况下，恢复这棵树。
     */

    private TreeNode prev = null;
    private TreeNode x = null;
    private TreeNode y = null;
    public void recoverTree(TreeNode root) {
        dfs(root);

        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;

        dfs(node.left);
        // 处理当前的节点
        if (prev != null && node.val < prev.val) {
            // node 不符合 BST 性质
            y = node;
            if (x == null) {
                x = prev;
            } else {
                return;
            }
        }
        prev = node;
        dfs(node.right);
    }
}
