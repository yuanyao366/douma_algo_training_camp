package com.douma._17_day_二叉树二._112;

import com.douma._17_day_二叉树二.TreeNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _112_PathSum_Final_Code {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return targetSum - root.val == 0;
        }

        boolean isLeftHasPathSum = hasPathSum(root.left, targetSum - root.val);
        // 提前退出
        if (isLeftHasPathSum) return true;
        boolean isRightHasPathSum = hasPathSum(root.right, targetSum - root.val);

        return isLeftHasPathSum || isRightHasPathSum;
    }
}
