package com.douma._18_day_二叉树三._538;

import com.douma._18_day_二叉树三.TreeNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _538_convert_bst_to_greater_tree {
    /* 538. 把二叉搜索树转换为累加树
     给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
     使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。

     提示：
        树中的节点数介于 0 和 10^4 之间。
        每个节点的值介于 -10^4 和 10^4 之间。
        树中的所有值 互不相同 。
        给定的树为二叉搜索树。
     */

    private int currSum = 0;
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    // 反着的中序遍历
    private void dfs(TreeNode node) {
        if (node == null) return;

        dfs(node.right);
        // 处理当前的根节点
        currSum += node.val;
        node.val = currSum;
        dfs(node.left);
    }
}
