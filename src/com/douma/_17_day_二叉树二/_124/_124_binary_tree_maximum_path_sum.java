package com.douma._17_day_二叉树二._124;

import com.douma._17_day_二叉树二.TreeNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _124_binary_tree_maximum_path_sum {
    /* 124. 二叉树中的最大路径和
    路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
    同一个节点在一条路径序列中 至多出现一次 。
    该路径 至少包含一个 节点，且不一定经过根节点。

    路径和 是路径中各节点值的总和。

    给你一个二叉树的根节点 root ，返回其 最大路径和 。

    提示：
    树中节点数目范围是 [1, 3 * 10^4]
    -1000 <= Node.val <= 1000

     */
    // bug 修复：因为是求最大值，所以初始化为最小值
    private int maxSum = Integer.MIN_VALUE;;
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    // 返回以 node 为根节点的子树的最大贡献值
    private int maxGain(TreeNode node) {
        if (node == null) return 0;

        // 递归计算左右子节点的最大贡献值
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        maxSum = Math.max(maxSum, leftGain + rightGain + node.val);

        return Math.max(leftGain, rightGain) + node.val;
    }
}
