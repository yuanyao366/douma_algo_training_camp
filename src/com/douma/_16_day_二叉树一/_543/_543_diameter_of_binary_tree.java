package com.douma._16_day_二叉树一._543;

import com.douma._16_day_二叉树一.TreeNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _543_diameter_of_binary_tree {
    /* 543. 二叉树的直径
        给定一棵二叉树，你需要计算它的直径长度。
        一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
        这条路径可能穿过也可能不穿过根结点。

        输入：
              1
             / \
            2   3
           / \
          4   5

         输出：3

         注意：两结点之间的路径长度是以它们之间边的数目表示。

              1
             / \
            2   3
           / \
          4   5
         /     \
        9       7
       /         \
      6           8
     */

    private int ans = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return ans;
    }

    // DFS 递归 - 后序遍历
    // 计算以 root 为根节点的二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);

        ans = Math.max(ans, leftMaxDepth + rightMaxDepth);

        // 处理根节点
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
}
