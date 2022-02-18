package com.douma._19_day_DFS_BFS._589;

import com.douma._19_day_DFS_BFS.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _589_n_ary_tree_preorder_traversal {
    /* 589. N 叉树的前序遍历
        给定一个 N 叉树，返回其节点值的 前序遍历 。
    N 叉树 在输入中按层序遍历进行序列化表示，
    每组子节点由空值 null 分隔（请参见示例）。

    输入：root = [1,null,3,2,4,null,5,6]
             1
          /  |  \
         3   2   4
       /  \
      5    6

      输出： [1, 3, 5, 6, 2, 4]

    进阶：
    递归法很简单，你可以使用迭代法完成此题吗?
     */

    // 迭代
    public List<Integer> preorder1(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root ==null) return res;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            res.add(curr.val);
            // 将 N 个子节点放入到栈中
            List<Node> children = curr.children;
            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }
        return res;
    }

    // 递归
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
        for (Node child : node.children) {
            dfs(child, res);
        }
    }
}
