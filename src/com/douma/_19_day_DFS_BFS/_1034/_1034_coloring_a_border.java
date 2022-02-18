package com.douma._19_day_DFS_BFS._1034;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1034_coloring_a_border {
    /* 1034. 边框着色
     给出一个二维整数网格 grid，网格中的每个值表示该位置处的网格块的颜色。

     只有当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一连通分量。

     连通分量的边界是指连通分量中的所有与不在分量中的正方形相邻（四个方向上）的所有正方形，
     或者在网格的边界上（第一行/列或最后一行/列）的所有正方形。

     给出位于 (r0, c0) 的网格块和颜色 color，
     使用指定颜色 color 为所给网格块的连通分量的边界进行着色，并返回最终的网格 grid 。
     */

    private int[][] grid;
    private int rows;
    private int cols;
    private boolean[][] visited;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int currColor;

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.visited = new boolean[rows][cols];

        this.currColor = grid[r0][c0];
        if (currColor == color) return grid;

        dfs(r0, c0, color);

        return grid;
    }

    private void dfs(int row, int col, int color) {
        visited[row][col] = true;

        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (!inArea(nextRow, nextCol)
                    || (grid[nextRow][nextCol] != currColor && !visited[nextRow][nextCol])) {
                grid[row][col] = color;
            } else if (!visited[nextRow][nextCol]){
                dfs(nextRow, nextCol, color);
            }
        }
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
