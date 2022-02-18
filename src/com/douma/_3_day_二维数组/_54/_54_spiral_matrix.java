package com.douma._3_day_二维数组._54;

import java.util.ArrayList;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _54_spiral_matrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        int startRow = 0, endRow = matrix.length - 1;
        int startCol = 0, endCol = matrix[0].length - 1;

        while (startRow <= endRow && startCol <= endCol) {
            // top 行
            for (int col = startCol; col <= endCol; col++) res.add(matrix[startRow][col]);
            // right 列
            for (int row = startRow + 1; row <= endRow; row++) res.add(matrix[row][endCol]);
            if (startRow < endRow && startCol < endCol) {
                // bottom 行
                for (int col = endCol - 1; col > startCol; col--) res.add(matrix[endRow][col]);
                // left 列
                for (int row = endRow; row > startRow; row--) res.add(matrix[row][startCol]);
            }
            startRow++;
            endRow--;
            startCol++;
            endCol--;
        }
        return res;
    }

    public List<Integer> spiralOrder1(int[][] matrix) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int m = matrix.length;
        int n = matrix[0].length;

        int row = 0, col = 0;
        int di = 0;
        List<Integer> res = new ArrayList<>();
        boolean[][] seen = new boolean[m][n];
        for (int i = 0; i < m * n; i++) {
            res.add(matrix[row][col]);
            seen[row][col] = true;

            int nextRow = row + dirs[di][0];
            int nextCol = col + dirs[di][1];

            if (nextRow < 0 || nextRow >= m
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
