package com.douma._3_day_二维数组._48;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _48_rotate_image {
    // 原地翻转
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int row = 0; row < n / 2; row++) {
            for (int col = 0; col < n; col++) {
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[n - row - 1][col];
                matrix[n - row - 1][col] = tmp;
            }
        }
        // 主对角线翻转
        for (int row = 0; row < n; row++) {
            // bug 修复：到 row 为止即可，要不然就翻转回来了
            for (int col = 0; col < row; col++) {
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = tmp;
            }
        }
    }
    // 原地旋转
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int row = 0; row < n / 2; row++) {
            for (int col = 0; col < (n + 1) / 2; col++) {
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[n - col - 1][row];
                matrix[n - col - 1][row] = matrix[n - row - 1][n - col - 1];
                matrix[n - row - 1][n - col - 1] = matrix[col][n - row - 1];
                matrix[col][n - row - 1] = tmp;
            }
        }
    }

    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        int[][] newMatrix = new int[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                newMatrix[col][n - row - 1] = matrix[row][col];
            }
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                matrix[row][col] = newMatrix[row][col];
            }
        }
    }
}
