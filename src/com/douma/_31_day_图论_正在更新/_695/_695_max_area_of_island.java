package com.douma._31_day_图论_正在更新._695;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _695_max_area_of_island {
    /* 695. 岛屿的最大面积
    给定一个包含了一些 0 和 1 的非空二维数组 grid 。

    一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，
    这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
    你可以假设 grid 的四个边缘都被 0（代表水）包围着。

    找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)

     */

    private int[][] grid;
    private int rows;
    private int cols;
    private boolean[][] visited;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.visited = new boolean[rows][cols];

        int ans = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1 && !visited[row][col]) {
                    int area = dfs(row, col);
                    ans = Math.max(area, ans);
                }
            }
        }

        return ans;
    }

    // DFS + 递归
    private int dfs(int row, int col) {
        if (!inArea(row, col) || grid[row][col] == 0 || visited[row][col]) {
            return 0;
        }

        // bug 修复：需要设置当前顶点已经被访问
        visited[row][col] = true;

        int res = 0;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            res += dfs(nextRow, nextCol);
        }

        return 1 + res;
    }

    // DFS + 迭代
    private int dfs1(int row, int col) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{row, col});
        visited[row][col] = true;

        int area = 0;
        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            area++;
            for (int[] dir : dirs) {
                int nextRow = curr[0] + dir[0];
                int nextCol = curr[1] + dir[1];
                if (inArea(nextRow, nextCol)
                        && grid[nextRow][nextCol] == 1
                        && !visited[nextRow][nextCol]) {
                    stack.push(new int[]{nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        return area;
    }

    // BFS
    private int bfs(int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        visited[row][col] = true;

        int area = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            area++;
            for (int[] dir : dirs) {
                int nextRow = curr[0] + dir[0];
                int nextCol = curr[1] + dir[1];
                if (inArea(nextRow, nextCol)
                        && grid[nextRow][nextCol] == 1
                        && !visited[nextRow][nextCol]) {
                    queue.add(new int[]{nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        return area;
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
