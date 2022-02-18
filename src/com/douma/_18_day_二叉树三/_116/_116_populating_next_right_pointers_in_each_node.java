package com.douma._18_day_二叉树三._116;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _116_populating_next_right_pointers_in_each_node {
    /* 116. 填充每个节点的下一个右侧节点指针
    给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。

    二叉树定义如下：
    struct Node {
      int val;
      Node *left;
      Node *right;
      Node *next;
    }
    填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
    如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
    初始状态下，所有 next 指针都被设置为 NULL。

    进阶：
    你只能使用常量级额外空间。
    使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
     */

    // 1. BFS 层序遍历
    public Node connect1(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                // 如果当前节点不是这一层的最后一个节点，则设置 next
                if (i != size - 1) curr.next = queue.peek();
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return root;
    }

    // 2. 双指针
    public Node connect3(Node root) {
        if (root == null) return null;
        Node left = root;
        Node curr = null;
        while (left.left != null) {
            curr = left;
            while (curr != null) {
                curr.left.next = curr.right;
                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            left = left.left;
        }
        return root;
    }

    // 3. DFS
    public Node connect(Node root) {
        dfs(root);
        return root;
    }

    private void dfs(Node node) {
        if (node == null) return;

        Node left = node.left;
        Node right = node.right;
        while (left != null) {
            left.next = right;
            left = left.right;
            right = right.left;
        }

        dfs(node.left);
        dfs(node.right);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
