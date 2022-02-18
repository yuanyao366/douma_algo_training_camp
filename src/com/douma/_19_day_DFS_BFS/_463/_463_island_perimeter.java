package com.douma._19_day_DFS_BFS._463;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _463_island_perimeter {
    /* 463. 岛屿的周长
        给定一个 row x col 的二维网格地图 grid ，
        其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。

        网格中的格子 水平和垂直 方向相连（对角线方向不相连）。
        整个网格被水完全包围，但其中恰好有一个岛屿
        （或者说，一个或多个表示陆地的格子相连组成的岛屿）。

        岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。

        格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。

        计算这个岛屿的周长。

        提示：
        row == grid.length
        col == grid[i].length
        1 <= row, col <= 100
        grid[i][j] 为 0 或 1
     */

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int[][] grid;
    private int rows;
    private int cols;

    private boolean[][] visited;

    private int res = 0;
    // 深度优先遍历
    public int islandPerimeter(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.visited = new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    return bfs(row, col);
                }
            }
        }

        return 0;
    }

    // 1. 前序遍历
    private void dfs1(int row, int col) {
        if (!inArea(row, col) || grid[row][col] == 0 || visited[row][col]) {
            return;
        }

        visited[row][col] = true;

        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (!inArea(nextRow, nextCol) || grid[nextRow][nextCol] == 0) {
                res += 1;
            }
            dfs1(nextRow, nextCol);
        }
    }

    // 2. 后序遍历
    private int dfs(int row, int col) {
        if (!inArea(row, col) || grid[row][col] == 0) return 1;
        if (visited[row][col]) return 0;

        visited[row][col] = true;

        int res = 0;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            res += dfs(nextRow, nextCol);
        }

        return res;
    }

    // 3. 广度优先遍历
    private int bfs(int row, int col) {
        int res = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, col});
        visited[row][col] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : dirs) {
                int nextRow = curr[0] + dir[0];
                int nextCol = curr[1] + dir[1];
                if (!inArea(nextRow, nextCol) || grid[nextRow][nextCol] == 0) {
                    res += 1;
                } else if (!visited[nextRow][nextCol]) {
                    queue.offer(new int[]{nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        return res;
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
