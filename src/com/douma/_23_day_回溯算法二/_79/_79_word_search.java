package com.douma._23_day_回溯算法二._79;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _79_word_search {
    /* 79. 单词搜索
    给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。
    如果 word 存在于网格中，返回 true ；否则，返回 false 。

    单词必须按照字母顺序，通过相邻的单元格内的字母构成，
    其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
    同一个单元格内的字母不允许被重复使用。


     */

    private char[][] board;
    private String word;

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m;
    private int n;
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        this.m = board.length;
        this.n = board[0].length;
        this.visited = new boolean[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == word.charAt(0)) {
                    if (dfs(row, col, 0)) return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int row, int col, int index) {
        if (board[row][col] != word.charAt(index)) return false;
        else if (index == word.length() - 1) return true;

        visited[row][col] = true;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n
                    && !visited[nextRow][nextCol]) {
                if (dfs(nextRow, nextCol, index + 1)) return true;
            }
        }
        visited[row][col] = false;
        return false;
    }
}
