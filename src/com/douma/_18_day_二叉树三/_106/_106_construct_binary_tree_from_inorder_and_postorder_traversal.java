package com.douma._18_day_二叉树三._106;

import com.douma._18_day_二叉树三.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _106_construct_binary_tree_from_inorder_and_postorder_traversal {
    /* 106. 从中序与后序遍历序列构造二叉树
    根据一棵树的中序遍历与后序遍历构造二叉树。

    注意:
    你可以假设树中没有重复的元素。

    例如，给出
    中序遍历 inorder = [9,3,15,20,7]
    后序遍历 postorder = [9,15,7,20,3]
    返回如下的二叉树：

        3
       / \
      9  20
        /  \
       15   7
     */
    private int[] postorder;
    private int rootIndex;
    private Map<Integer, Integer> idxMap;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.rootIndex = postorder.length - 1;
        this.idxMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            idxMap.put(inorder[i], i);
        }
        return buildTree(0, inorder.length - 1);
    }

    private TreeNode buildTree(int left, int right) {
        if (left > right) return null;

        int rootVal = postorder[rootIndex];
        rootIndex--;
        TreeNode root = new TreeNode(rootVal);
        int mid = idxMap.get(rootVal);

        root.right = buildTree(mid + 1, right);
        root.left = buildTree(left, mid - 1);

        return root;
    }
}
