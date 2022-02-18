package com.douma._8_day_二分查找._240;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _240_search_a_2d_matrix_ii {
    // 暴力解法
    // 时间复杂度 O(mn)
    // 空间复杂度 O(1)
    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }

        return false;
    }

    // 二分查找
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        int shortDim = Math.min(m, n);
        for (int i = 0; i < shortDim; i++) {
            boolean rowFound = binarySearchRow(matrix, i, target);
            boolean colFount = binarySearchCol(matrix, i, target);

            if (rowFound || colFount) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearchRow(int[][] matrix,
                                    int row, int target) {
        int lo = row;
        int hi = matrix[0].length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return false;
    }

    private boolean binarySearchCol(int[][] matrix,
                                    int col, int target) {
        int le = col;
        int hi = matrix.length - 1;

        while (le <= hi) {
            int mid = le + (hi - le) / 2;
            if (matrix[mid][col] == target) {
                return true;
            } else if (matrix[mid][col] < target) {
                le = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return false;
    }

    // 缩减搜索空间
    public boolean searchMatrix(int[][] matrix,
                                       int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        int i = m - 1;
        int j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] < target) j++;
            else if (matrix[i][j] > target) i--;
            else return true;
        }

        return false;
    }




}
