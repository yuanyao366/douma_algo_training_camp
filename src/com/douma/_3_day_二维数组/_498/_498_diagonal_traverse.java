package com.douma._3_day_二维数组._498;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _498_diagonal_traverse {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dirs = {{-1, 1}, {1, -1}};

        int row = 0, col = 0, di = 0;
        int[] result = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            result[i] = matrix[row][col];

            row = row + dirs[di][0];
            col = col + dirs[di][1];

            if (col >= n) {col = n - 1; row += 2; di = 1 - di;}
            if (row >= m) {row = m - 1; col += 2; di = 1 - di;}
            if (row < 0) {row = 0; di = 1 - di;}
            if (col < 0) {col = 0; di = 1 - di;}
        }
        return result;
    }
}
