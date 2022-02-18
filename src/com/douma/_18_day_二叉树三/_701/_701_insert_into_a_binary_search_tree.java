package com.douma._18_day_二叉树三._701;

import com.douma._18_day_二叉树三.TreeNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _701_insert_into_a_binary_search_tree {
    /* 701. 二叉搜索树中的插入操作

        给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
        返回插入后二叉搜索树的根节点。
        输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。

        注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。
        你可以返回 任意有效的结果 。

        提示：
        给定的树上的节点数介于 0 和 10^4 之间
        每个节点都有一个唯一整数值，取值范围从 0 到 10^8
        -10^8 <= val <= 10^8
        新值和原始二叉搜索树中的任意节点值都不同
     */
    // 1. 迭代
    // O(logn)
    public TreeNode insertIntoBST1(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode curr = root;
        while (curr != null) {
            if (val < curr.val && curr.left == null) {
                curr.left = new TreeNode(val);
                return root;
            } else if (val > curr.val && curr.right == null) {
                curr.right = new TreeNode(val);
                return root;
            }

            if (val < curr.val) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return root;
    }

    // 递归
    // 将 val 插入到以 root 为根节点的二叉搜索树中，并返回插入节点后的二叉搜索树的根节点
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }
}
