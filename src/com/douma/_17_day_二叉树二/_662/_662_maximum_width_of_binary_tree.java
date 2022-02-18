package com.douma._17_day_二叉树二._662;

import com.douma._17_day_二叉树二.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _662_maximum_width_of_binary_tree {
    /* 662. 二叉树最大宽度

        给定一个二叉树，编写一个函数来获取这个树的最大宽度。
        树的最大宽度是所有层中的最大宽度。
        这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。

        每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。

        输入:
           1
         /   \
        3     2
       / \     \
      5   3     9
        输出: 4

        输入:
          1
         /
        3
       / \
      5   3
        输出: 2

        输入:
          1
         / \
        3   2
       /
      5

        输出: 2

        输入:
          1
         / \
        3   2
       /     \
      5       9
     /         \
    6           7
        输出: 8

     */

    private class Node {
        TreeNode node;
        int seqNo;

        Node(TreeNode node, int seqNo) {
            this.node = node;
            this.seqNo = seqNo;
        }
    }

    // BFS
    public int widthOfBinaryTree1(TreeNode root) {
        if(root == null) return 0;
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(new Node(root, 1));
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int startSeqNo = 0, endSeqNo = 0;
            for(int i = 0; i < size; i++){
                Node curr = queue.poll();
                TreeNode node = curr.node;
                int seqNo = curr.seqNo;
                if (i == 0) startSeqNo = seqNo;
                if (i == size - 1) endSeqNo = seqNo;
                if (node.left != null) {
                    queue.offer(new Node(node.left, 2 * seqNo));
                }
                if (node.right != null) {
                    queue.offer(new Node(node.right, 2 * seqNo + 1));
                }
            }
            maxWidth = Math.max(maxWidth, endSeqNo - startSeqNo + 1);
        }
        return maxWidth;
    }


    // DFS
    public int widthOfBinaryTree(TreeNode root) {
        return dfs(root, 0, 1, new ArrayList<>(), new ArrayList<>());
    }

    private int dfs(TreeNode node, int level, int seqNo,
                    List<Integer> start, List<Integer> end) {
        if (node == null) return 0;

        // 前序 - 递
        if (start.size() == level) { //level 层的第一个节点
            start.add(seqNo);
            end.add(seqNo);
        } else {
            end.set(level, seqNo);
        }

        int leftMaxWidth = dfs(node.left, level + 1, 2 * seqNo, start, end);
        int rightMaxWidth = dfs(node.right, level + 1, 2 * seqNo + 1, start, end);

        // 后序，回溯 - 归
        int currWidth = end.get(level) - start.get(level) + 1;
        return Math.max(currWidth, Math.max(leftMaxWidth, rightMaxWidth));
    }
}
