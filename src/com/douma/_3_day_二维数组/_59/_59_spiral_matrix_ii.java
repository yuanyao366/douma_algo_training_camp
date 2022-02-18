package com.douma._3_day_二维数组._59;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _59_spiral_matrix_ii {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];

        int startRow = 0, endRow = n - 1;
        int startCol = 0, endCol = n - 1;

        int i = 1;
        while (startRow <= endRow && startCol <= endCol) {
            // top 行
            for (int col = startCol; col <= endCol; col++) res[startRow][col] = i++;
            // right 列
            for (int row = startRow + 1; row <= endRow; row++) res[row][endCol] = i++;
            if (startRow < endRow && startCol < endCol) {
                // bottom 行
                for (int col = endCol - 1; col > startCol; col--) res[endRow][col] = i++;
                // left 列
                for (int row = endRow; row > startRow; row--) res[row][startCol] = i++;
            }
            startRow++;
            endRow--;
            startCol++;
            endCol--;
        }
        return res;
    }
    public int[][] generateMatrix1(int n) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int row = 0, col = 0;
        int di = 0;
        int[][] res = new int[n][n];
        boolean[][] seen = new boolean[n][n];
        for (int i = 0; i < n * n; i++) {
            res[row][col] = i + 1;
            seen[row][col] = true;

            int nextRow = row + dirs[di][0];
            int nextCol = col + dirs[di][1];

            if (nextRow < 0 || nextRow >= n
                    || nextCol < 0 || nextCol >= n
                    || seen[nextRow][nextCol]) {
                // 改变方向
                di = (di + 1) % 4;
            }

            row = row + dirs[di][0];
            col = col + dirs[di][1];
        }
        return res;
    }
}
