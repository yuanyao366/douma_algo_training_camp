package com.douma.笔试代码.zoom._20220810;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */

class TreeNode {
    TreeNode left;
    TreeNode right;
    boolean isRed; // true 表示红节点，false 表示蓝色节点
    // 用于记录到这个节点为止，路径上红色节点的个数
    int redNum;
    // 用于记录到这个节点为止，路径上蓝色节点的个数
    int blueNum;

    TreeNode(boolean isRed) {
        this.isRed = isRed;
        this.redNum = isRed ? 1 : 0;
        this.blueNum = isRed ? 0 : 1;
    }

    int getWeight() {
        return Math.abs(redNum - blueNum);
    }
}

public class _1_TreeTotalWeight {
    /**
     * 求树的权值
     *
     * 告诉你一棵树的节点分为蓝色和红色，一个节点的权值定义为：
     * 从根节点到该节点路径上的红蓝节点个数之差的绝对值
     *
     * 求整棵树的权值之和
     */

    int res = 0;

    public int totalWeight(TreeNode root) {
        if (root == null) return res;

        res += root.getWeight();

        dfs(root);

        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;

        if (node.left != null) {
            node.left.redNum += node.redNum;
            node.left.blueNum += node.blueNum;
            res += node.left.getWeight();
            dfs(node.left);
        }
        if (node.left != null) {
            node.right.redNum += node.redNum;
            node.right.blueNum += node.blueNum;
            res += node.right.getWeight();
            dfs(node.right);
        }
    }

    public static void main(String[] args) {
        /**
         构造一颗下面的树
                    红(1)
                  /    \
                蓝(0)   红(2)
              /   \
           红(1)  蓝(1)
         */
        TreeNode root = new TreeNode(true);

        TreeNode node1 = new TreeNode(false);
        root.left = node1;

        TreeNode node2 = new TreeNode(true);
        root.right = node2;

        TreeNode node3 = new TreeNode(true);
        node1.left = node3;

        TreeNode node4 = new TreeNode(false);
        node1.right = node4;

        System.out.println(new _1_TreeTotalWeight().totalWeight(root));
    }
}
