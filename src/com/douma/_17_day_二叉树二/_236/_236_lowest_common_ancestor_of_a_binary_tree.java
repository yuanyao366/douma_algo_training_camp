package com.douma._17_day_二叉树二._236;

import com.douma._17_day_二叉树二.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _236_lowest_common_ancestor_of_a_binary_tree {
    /* 236. 二叉树的最近公共祖先
    给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

    百度百科中最近公共祖先的定义为：
        “对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
        满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

    树中节点数目在范围 [2, 10^5] 内。
    -10^9 <= Node.val <= 10^9
    所有 Node.val 互不相同 。
    p != q
    p 和 q 均存在于给定的二叉树中。
     */

    // 根据节点与父亲节点关系来实现
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // 1. 维护子节点与其对应父节点的关系
        Map<Integer, TreeNode> parent = new HashMap<>();
        dfs(root, parent);

        // 2. 从节点 p 开始，依次访问它的祖先
        Set<Integer> visited = new HashSet<>();
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }

        // 3. 从节点 q 开始，依次访问它的祖先
        // 如果第一次遇到了 p 的祖先的话，那么这个祖先就是最近的公共祖先
        while (q != null) {
            if (visited.contains(q.val)) return q;
            q = parent.get(q.val);
        }

        return null;
    }

    private void dfs(TreeNode node, Map<Integer, TreeNode> parent) {
        if (node == null) return;

        if (node.left != null) parent.put(node.left.val, node);
        if (node.right != null) parent.put(node.right.val, node);

        dfs(node.left, parent);
        dfs(node.right, parent);
    }

    // DFS 后序遍历
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null) return right;
        if (right == null) return left;

        return root;
    }
}
