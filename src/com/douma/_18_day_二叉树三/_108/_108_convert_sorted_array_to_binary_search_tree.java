package com.douma._18_day_二叉树三._108;

import com.douma._18_day_二叉树三.TreeNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _108_convert_sorted_array_to_binary_search_tree {
    /* 108. 将有序数组转换为二叉搜索树

    给你一个整数数组 nums ，其中元素已经按 升序 排列，
    请你将其转换为一棵 高度平衡 二叉搜索树。

    高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。

    输入：nums = [-10,-3,0,5,9]
    输出：
         0
       /   \
      -3    9
     /     /
   -10    5

   或者：
         0
       /   \
     -10    5
       \     \
      -3      9

    1 <= nums.length <= 10^4
    -10^4 <= nums[i] <= 10^4
    nums 按 严格递增 顺序排列
     */

    // 思考：如果把数组变成链表的话，你会怎么实现呢？见 leetcode ：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = buildBST(nums, left, mid - 1);
        root.right = buildBST(nums, mid + 1, right);
        return root;
    }
}
