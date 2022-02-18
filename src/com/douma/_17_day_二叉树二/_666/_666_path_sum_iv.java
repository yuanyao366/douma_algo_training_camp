package com.douma._17_day_二叉树二._666;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _666_path_sum_iv {
    /* 666. 路径总和 IV
    对于一棵深度小于 5 的树，可以用一组三位十进制整数来表示。

    对于每个整数：
        1. 百位上的数字表示这个节点的深度 D，1 <= D <= 4。
        2. 十位上的数字表示这个节点在当前层所在的位置 P， 1 <= P <= 8。
            位置编号与一棵满二叉树的位置编号相同。
        3.个位上的数字表示这个节点的权值 V，0 <= V <= 9。
    给定一个包含三位整数的升序数组，表示一棵深度小于 5 的二叉树，
    请你返回从根到所有叶子结点的路径之和。

    输入: [113, 215, 221]
    输出: 12
    解释:
        3
       / \
      5   1
    路径和 = (3 + 5) + (3 + 1) = 12

     */
    private int ans = 0;
    public int pathSum(int[] nums) {
        // 构建二叉树，使用数组存储
        Integer[] tree = new Integer[15];
        for (int num : nums) {
            int bai = num / 100;
            int shi = num % 100 / 10;
            int ge = num % 10;
            int index = ((1 << (bai - 1)) - 1) + shi - 1;
            tree[index] = ge;
        }

        // DFS 遍历树
        dfs(tree, 0, 0);
        return ans;
    }

    private void dfs(Integer[] tree, int i, int currPathSum) {
        if (tree[i] == null) return;

        currPathSum += tree[i];
        if (i >= 7 || (tree[2 * i + 1] == null && tree[2 * i + 2] == null)) {
            ans += currPathSum;
            return;
        }

        dfs(tree, 2 * i + 1, currPathSum);
        dfs(tree, 2 * i + 2, currPathSum);
    }
}
