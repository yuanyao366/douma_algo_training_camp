package com.douma._18_day_二叉树三._98;

import com.douma._18_day_二叉树三.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _98_validate_binary_search_tree {
    /* 98. 验证二叉搜索树
    给定一个二叉树，判断其是否是一个有效的二叉搜索树。
    假设一个二叉搜索树具有如下特征：
        1.节点的左子树只包含小于当前节点的数。
        2.节点的右子树只包含大于当前节点的数。
        3. 所有左子树和右子树自身必须也是二叉搜索树。

     输入:
        2
       / \
      1   3
    输出: true

    输入:
        5
       / \
      1   4
         / \
        3   6
    输出: false
     */

    // 1. 中序遍历(先拿到遍历结果，再验证顺序性)
    public boolean isValidBST1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder1(root, res);

        for (int i = 1; i < res.size(); i++) {
            if (res.get(i) <= res.get(i - 1)) return false;
        }

        return true;
    }

    private void inorder1(TreeNode node, List<Integer> res) {
        if (node == null) return;

        inorder1(node.left, res);
        res.add(node.val);
        inorder1(node.right, res);
    }

    // 2. 中序遍历 (边遍历边验证顺序性)
    private TreeNode prev = null;
    private boolean isBST = true;
    public boolean isValidBST(TreeNode root) {

        inorder(root);

        return isBST;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);
        if (prev != null && node.val <= prev.val) {
            // 不符合 BST 性质
            isBST = false;
            return;
        }
        prev = node;
        inorder(node.right);
    }

    // 3. 后序遍历
    public boolean isValidBST3(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode node, long lower, long upper) {
        if (node == null) return true;

        if (node.val <= lower || node.val >= upper) {
            return false;
        }

        boolean isLeftBST = dfs(node.left, lower, node.val);
        boolean isRightBST = dfs(node.right, node.val, upper);

        return isLeftBST && isRightBST;
    }
}
