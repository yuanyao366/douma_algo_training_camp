package com.douma._17_day_二叉树二._114;

import com.douma._17_day_二叉树二.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _114_flatten_binary_tree_to_linked_list {
    /* 114. 二叉树展开为链表
        给你二叉树的根结点 root ，请你将它展开为一个单链表：

        1. 展开后的单链表应该同样使用 TreeNode ，
        其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
        2. 展开后的单链表应该与二叉树 先序遍历 顺序相同。

        提示：
        树中结点数在范围 [0, 2000] 内
        -100 <= Node.val <= 100

        进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
     */

    // 先前序遍历，得到结果，在串联成链表
    public void flatten1(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        preorder(root, list);
        for (int i = 1; i < list.size(); i++) {
            TreeNode prev = list.get(i - 1);
            TreeNode curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    public void preorder(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        list.add(root);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    // 边遍历边串联
    public void flatten2(TreeNode root) {
        if (root == null) return;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (prev != null) {
                prev.left = null;
                prev.right = curr;
            }
            TreeNode left = curr.left, right = curr.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
            prev = curr;
        }
    }

    // 原地改变指针
    public void flatten(TreeNode root) {
        if (root == null) return;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode left = curr.left;
                TreeNode pre = left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = curr.right;
                curr.left = null;
                curr.right = left;
            }
            curr = curr.right;
        }
    }
}
