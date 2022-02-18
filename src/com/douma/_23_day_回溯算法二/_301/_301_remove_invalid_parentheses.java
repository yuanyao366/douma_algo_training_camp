package com.douma._23_day_回溯算法二._301;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _301_remove_invalid_parentheses {
    /* 301. 删除无效的括号
    给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
    返回所有可能的结果。答案可以按 任意顺序 返回。

    示例 1：
    输入：s = "()())()"
    输出：["()()()", "(())()"]

    示例 2：
    输入：s = "(a)())()"
    输出：["(a)()()", "(a())()"]

    示例 3：
    输入：s = ")("
    输出：[""]
     
    提示：
    1 <= s.length <= 25
    s 由小写英文字母以及括号 '(' 和 ')' 组成
    s 中至多含 20 个括号

     */

    // BFS
    public List<String> removeInvalidParentheses1(String s) {
        List<String> res = new ArrayList<>();

        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        Set<String> visited = new HashSet<>();
        visited.add(s);

        boolean found = false;
        while (!queue.isEmpty()) {
            // 最优解一定在同一层
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currStr = queue.remove();
                if (isValid(currStr)) {
                    res.add(currStr);
                    found = true;
                    continue;
                }
                int currStrLen = currStr.length();
                for (int j = 0; j < currStrLen; j++) {
                    if (currStr.charAt(j) != '(' && currStr.charAt(j) != ')') continue;

                    String leftStr = currStr.substring(0, j);
                    String rightStr = (j == currStrLen - 1) ?
                            "" : currStr.substring(j + 1, currStrLen);
                    String next = leftStr + rightStr;
                    if (!visited.contains(next)) {
                        queue.add(next);
                        visited.add(next);
                    }
                }
            }
            if (found) break;
        }
        return res;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;

            if (count < 0) return false;
        }
        return count == 0;
    }

    private String s;
    private Set<String> res;
    // 回溯 (DFS)
    public List<String> removeInvalidParentheses(String s) {
        this.s = s;
        this.res = new HashSet<>();

        // 第 1 步：遍历一次，计算多余的左右括号
        int leftRemove = 0;
        int rightRemove = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftRemove++;
            } else if (s.charAt(i) == ')') {
                // 遇到右括号的时候，须要根据已经存在的左括号数量决定
                if (leftRemove == 0) {
                    rightRemove++;
                }
                if (leftRemove > 0) {
                    // 关键：一个右括号出现可以抵销之前遇到的左括号
                    leftRemove--;
                }
            }
        }

        // 第 2 步：回溯算法，尝试每一种可能的删除操作
        dfs(0, leftRemove, rightRemove, 0, 0, new StringBuilder());
        return new ArrayList<>(res);
    }

    private void dfs(int index,
                     int leftRemove, int rightRemove,
                     int leftCount, int rightCount,
                     StringBuilder path) {
        if (index == s.length()) {
            if (leftRemove == 0 && rightRemove == 0) {
                res.add(path.toString());
            }
            return;
        }

        // 先处理当前的字符
        char c = s.charAt(index);

        // 可能的操作 1：删除当前遍历到的字符
        if (c == '(' && leftRemove > 0) {
            dfs(index + 1, leftRemove - 1, rightRemove, leftCount, rightCount, path);
        }
        if (c == ')' && rightRemove > 0) {
            dfs(index + 1, leftRemove, rightRemove - 1, leftCount, rightCount, path);
        }

        path.append(c);
        // 可能的操作 2：保留当前遍历到的字符
        if (c != '(' && c != ')') {
            dfs(index + 1, leftRemove, rightRemove, leftCount, rightCount, path);
        } else if (c == '(') {
            dfs(index + 1, leftRemove, rightRemove, leftCount + 1, rightCount, path);
        } else if (rightCount < leftCount) {
            dfs(index + 1, leftRemove, rightRemove, leftCount, rightCount + 1, path);
        }
        path.deleteCharAt(path.length() - 1);
    }
}
