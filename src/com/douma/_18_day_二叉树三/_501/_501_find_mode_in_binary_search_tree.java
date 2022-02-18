package com.douma._18_day_二叉树三._501;

import com.douma._18_day_二叉树三.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _501_find_mode_in_binary_search_tree {
    /* 501. 二叉搜索树中的众数
        给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

        给定 BST [1,null,2,2],

           1
            \
             2
            /
           2
        返回[2].

        提示：如果众数超过1个，不需考虑输出顺序

        进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
     */

    private int prevNum = 0;
    private int count = 0;
    private int maxCount = 0;
    private List<Integer> ans = new ArrayList<>();

    // 中序遍历
    public int[] findMode(TreeNode root) {
        dfs(root);

        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        update(node.val);
        dfs(node.right);
    }

    private void update(int val) {
        if (val == prevNum) {
            count++;
        } else {
            prevNum = val;
            count = 1;
        }

        if (count == maxCount) {
            ans.add(val);
        } else if (count > maxCount) {
            ans.clear();
            ans.add(val);
            maxCount = count;
        }
    }
}
