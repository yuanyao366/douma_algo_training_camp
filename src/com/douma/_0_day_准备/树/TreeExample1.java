package com.douma._0_day_准备.树;


import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class TreeExample1 {

    /* 力扣 98. 验证二叉搜索树
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

    /*
    ACM 模式输入描述:
    第一行两个数 n, root，分别表示二叉树有 n 个节点，第 root 个节点时二叉树的根
    接下来共 n 行，第 i 行三个数 val_i, left_i, right_i，分别表示第 i 个节点的值 val，左儿子，右儿子
    值为 null 则表示空节点。

    输入:
        5 1
        5 2 3
        1 null null
        3 4 5
        4 null null
        6 null null
     */

    static class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int rootIndex = scanner.nextInt();
        scanner.nextLine(); // 换新的一行

        // 1. 构造一棵树
        // 记录每个节点
        TreeNode[] tree = new TreeNode[n];
        // 记录每个节点的左右子节点的值
        String[][] leaf = new String[n][2];
        for (int i = 0; i < n; i++) {
            String[] sArr = scanner.nextLine().split(" ");
            TreeNode node = new TreeNode(Integer.valueOf(sArr[0]));
            leaf[i][0] = sArr[1];
            leaf[i][1] = sArr[2];
            tree[i] = node;
        }

        for (int i = 0; i < n; i++) {
            // 左子节点
            String left = leaf[i][0];
            if (!"null".equals(left)) {
                // 这里减 1 是因为：节点值从 1 开始，而索引从 0 开始
                tree[i].left = tree[Integer.valueOf(left) - 1];
            }

            // 右子节点
            String right = leaf[i][1];
            if (!"null".equals(right)) {
                tree[i].right = tree[Integer.valueOf(right) - 1];
            }
        }

        // 2. 拿到树的根节点
        TreeNode root = tree[rootIndex];

        // 3. 判断这棵树是否是二叉查找树
        boolean res = isValidBST(root);

        // 4. 打印结果
        System.out.println(res);
    }

    // 3. 后序遍历
    private static boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean dfs(TreeNode node, long lower, long upper) {
        if (node == null) return true;

        if (node.val <= lower || node.val >= upper) {
            return false;
        }

        boolean isLeftBST = dfs(node.left, lower, node.val);
        boolean isRightBST = dfs(node.right, node.val, upper);

        return isLeftBST && isRightBST;
    }
}
