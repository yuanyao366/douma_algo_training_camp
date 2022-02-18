package com.douma._19_day_DFS_BFS._130;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _130_surrounded_regions {
    /* 130. 被围绕的区域
    给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，
    找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

    输入：board = [["X","X","X","X"],
                  ["X","O","O","X"],
                  ["X","X","O","X"],
                  ["X","O","X","X"]]

    输出：[["X","X","X","X"],
          ["X","X","X","X"],
          ["X","X","X","X"],
          ["X","O","X","X"]]

    解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
    任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
    如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     */

    private char[][] board;
    private int rows;
    private int cols;
    private boolean[][] visited;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void solve(char[][] board) {
        this.board = board;
        this.rows = board.length;
        this.cols = board[0].length;
        this.visited = new boolean[rows][cols];

        // 从四周查找 O 字母
        for (int col = 0; col < cols; col++) {
            // 1. 第一行
            if (board[0][col] == 'O' && !visited[0][col]) {
                dfs(0, col);
            }

            // 2. 最后一行
            if (board[rows - 1][col] == 'O' && !visited[rows - 1][col]) {
                dfs(rows - 1, col);
            }
        }

        for (int row = 1; row < rows - 1; row++) {
            // 3. 第一列
            if (board[row][0] == 'O' && !visited[row][0]) {
                dfs(row, 0);
            }

            // 4. 最后一列
            if (board[row][cols - 1] == 'O' && !visited[row][cols - 1]) {
                dfs(row, cols - 1);
            }
        }

        // 将没有被访问过并且等于 O 的字母填充为 X
        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                if (board[row][col] == 'O' && !visited[row][col]) {
                    board[row][col] = 'X';
                }
            }
        }
    }

    private void dfs(int row, int col) {
        if (!inArea(row, col) || board[row][col] == 'X' || visited[row][col])
            return;

        visited[row][col] = true;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            dfs(nextRow, nextCol);
        }
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
