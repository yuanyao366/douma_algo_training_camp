package com.douma.笔试代码.tengxun._20210826;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class _1_TreePruning {

    public TreeNode pruning(TreeNode root) {
        return trimLeafNodes(root);
    }

    // 修剪以 node 为根节点的树中的叶子节点，并返回修剪后的树的根节点
    private TreeNode trimLeafNodes(TreeNode node) {
        // 如果树是空的，或者只有一个节点，那么直接返回空树
        if (node == null || isLeaf(node)) return null;

        // 如果当前节点 node 的左右孩子有一个是叶子节点
        // 那么删除这个节点，也就是将这个节点从树中脱离掉，并返回空树
        if (isLeaf(node.left) || isLeaf(node.right)) {
            return null;
        }

        // 递归的去左子树、右子树中修剪叶子节点
        node.left = trimLeafNodes(node.left);
        node.right = trimLeafNodes(node.right);
        return node;
    }

    private boolean isLeaf(TreeNode node) {
        if (node == null) return false;
        return node.left == null && node.right == null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(8);
        TreeNode node8 = new TreeNode(9);
        TreeNode node9 = new TreeNode(10);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node3.left = node4;
        node3.right = node5;

        node2.left = node6;
        node6.right = node7;

        node2.right = node8;
        node8.right = node9;

        TreeNode res = new _1_TreePruning().pruning(root);
        System.out.println(res);
    }
}
