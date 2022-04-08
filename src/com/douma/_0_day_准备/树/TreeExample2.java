package com.douma._0_day_准备.树;


import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class TreeExample2 {

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
        第一行表示所有的节点

    输入:
        5,1,4,null,null,3,6

    说明：
        (1) 索引为 0 的节点 5 是根节点
        (2) 索引为 0 的节点的左节点是：2 * 0 + 1 = 1，也就是索引为 1 的节点
        (3) 索引为 0 的节点的右节点是：2 * 0 + 2 = 2，也就是索引为 2 的节点
        (2) 索引为 1 的节点的左节点是：2 * 1 + 1 = 3，也就是索引为 3 的节点
        (3) 索引为 1 的节点的右节点是：2 * 1 + 2 = 4，也就是索引为 4 的节点
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

        String[] values = scanner.nextLine().split(",");
        int n = values.length;

        // 1. 构造一棵树
        TreeNode[] nodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            String val = values[i];
            if (!"null".equals(val)) {
                nodes[i] = new TreeNode(Integer.valueOf(val));
            }
        }

        for (int i = 0; i * 2 + 2 < n; i++) {
            if (nodes[i] != null) {
                nodes[i].left = nodes[2 * i + 1];
                nodes[i].right = nodes[2 * i + 2];
            }
        }

        // 2. 拿到树的根节点
        TreeNode root = nodes[0];

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
