package com.douma._18_day_二叉树三._235;

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
public class _235_lowest_common_ancestor_of_a_binary_search_tree {
    /* 235. 二叉搜索树的最近公共祖先
        给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

        说明:
        所有节点的值都是唯一的。
        p、q 为不同节点且均存在于给定的二叉搜索树中。
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
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);

        if (left == null) return right;
        if (right == null) return left;

        return root;
    }

    // 利用 BST 的性质
    // 时间复杂度：O(logn)
    // 空间复杂度：O(1)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (ancestor != null) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }
}
