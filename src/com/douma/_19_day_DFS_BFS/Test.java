package com.douma._19_day_DFS_BFS;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class Test {

    private Set<Node> visited = new HashSet<>();
    // 图的 DFS
    public List<Integer> preorder(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root ==null) return res;
        dfs(root, res);
        return res;
    }

    private void dfs(Node node, List<Integer> res) {
        if (node == null) return;
        // 处理当前遍历的节点
        res.add(node.val);
        visited.add(node);
        for (Node child : node.children) {
            if (!visited.contains(child)) dfs(child, res);
        }
    }

    // 图的 BFS
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一层的节点
            int size = queue.size();
            List<Integer> levelNodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                levelNodes.add(curr.val);
                for (Node child : curr.children) {
                    if (!visited.contains(child)) queue.offer(child);
                    visited.add(child);
                }
            }
            res.add(levelNodes);
        }

        return res;
    }

    public static void main(String[] args) {
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        List<Node> children2 = Arrays.asList(node5, node6);
        Node node2 = new Node(2);
        node2.children = children2;

        Node node4 = new Node(4);
        Node node3 = new Node(3);

        List<Node> children1 = Arrays.asList(node2, node3, node4);
        Node root = new Node(1);
        root.children = children1;

        node5.children = Arrays.asList(root);

        List<List<Integer>> res = new Test().levelOrder(root);
        System.out.println(res);
    }
}
