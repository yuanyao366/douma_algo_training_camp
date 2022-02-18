package com.douma._3_day_二维数组._36;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _36_valid_sudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowUsed = new boolean[9][9];
        boolean[][] colUsed = new boolean[9][9];
        boolean[][] boxUsed = new boolean[9][9];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] != '.') {
                    int num = board[row][col] - '1';

                    if (rowUsed[row][num]) return false;
                    else rowUsed[row][num] = true;

                    if (colUsed[col][num]) return false;
                    else colUsed[col][num] = true;

                    int boxIndex = row / 3 + (col / 3) * 3;
                    if (boxUsed[boxIndex][num]) return false;
                    else boxUsed[boxIndex][num] = true;
                }
            }
        }
        return true;
    }
}
