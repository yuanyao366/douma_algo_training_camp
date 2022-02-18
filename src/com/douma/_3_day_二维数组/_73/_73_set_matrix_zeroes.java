package com.douma._3_day_二维数组._73;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _73_set_matrix_zeroes {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 先计算第一列是否包含 0
        boolean flagCol1 = false;

        // 使用 matrix 的第一行和第一列来记录每行每列是否包含 0
        for (int row = 0; row < m; row++) {
            // 计算第一列是否包含 0
            if (matrix[row][0] == 0) flagCol1 = true;

            for (int col = 1; col < n; col++) {
                if (matrix[row][col] == 0) {
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }

        // bug 修复：row--
        for (int row = m - 1; row >= 0; row--) {
            for (int col = 1; col < n; col++) {
                if (matrix[0][col] == 0 || matrix[row][0] == 0) {
                    matrix[row][col] = 0;
                }
            }
            if (flagCol1) {
                matrix[row][0] = 0;
            }
        }
    }

    public void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 先计算第一行第一列是否包含 0
        boolean flagRow1 = false;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) flagRow1 = true;
        }
        boolean flagCol1 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) flagCol1 = true;
        }

        // 使用 matrix 的第一行和第一列来记录每行每列是否包含 0
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (matrix[row][col] == 0) {
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (matrix[0][col] == 0 || matrix[row][0] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }

        if (flagRow1) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
        if (flagCol1) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public void setZeroes1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 记录每行或者每列是否包含 0
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == 0) {
                    rows[row] = true;
                    cols[col] = true;
                }
            }
        }

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rows[row] || cols[col]) {
                    matrix[row][col] = 0;
                }
            }
        }
    }
}
