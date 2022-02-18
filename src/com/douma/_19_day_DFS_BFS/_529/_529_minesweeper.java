package com.douma._19_day_DFS_BFS._529;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _529_minesweeper {
    /* 529. 扫雷游戏
    让我们一起来玩扫雷游戏！

    给定一个代表游戏板的二维字符矩阵。 
    'M' 代表一个未挖出的地雷，
    'E' 代表一个未挖出的空方块，
    'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，
    数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，
    'X' 则表示一个已挖出的地雷。

    现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），
    根据以下规则，返回相应位置被点击后对应的面板：
        1. 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
        2. 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），
        并且所有和其相邻的未挖出方块都应该被递归地揭露。
        3. 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），
        表示相邻地雷的数量。
        4. 如果在此次点击中，若无更多方块可被揭露，则返回面板。

    示例 1：
    输入:
    [['E', 'E', 'E', 'E', 'E'],
     ['E', 'E', 'M', 'E', 'E'],
     ['E', 'E', 'E', 'E', 'E'],
     ['E', 'E', 'E', 'E', 'E']]

    Click : [3,0]

    输出:
    [['B', '1', 'E', '1', 'B'],
     ['B', '1', 'M', '1', 'B'],
     ['B', '1', '1', '1', 'B'],
     ['B', 'B', 'B', 'B', 'B']]

    示例二：
    输入:
    [['B', '1', 'E', '1', 'B'],
     ['B', '1', 'M', '1', 'B'],
     ['B', '1', '1', '1', 'B'],
     ['B', 'B', 'B', 'B', 'B']]

    Click : [1,2]

    输出:
    [['B', '1', 'E', '1', 'B'],
     ['B', '1', 'X', '1', 'B'],
     ['B', '1', '1', '1', 'B'],
     ['B', 'B', 'B', 'B', 'B']]
     */

    private char[][] board;
    private int rows;
    private int cols;
    private boolean[][] visited;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        this.board = board;
        this.rows = board.length;
        this.cols = board[0].length;
        this.visited = new boolean[rows][cols];

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        } else {
            dfs(click[0], click[1]);
        }

        return board;
    }

    private void dfs(int row, int col) {
        if (!inArea(row, col) || board[row][col] != 'E' || visited[row][col])
            return;

        visited[row][col] = true;

        int mines = 0;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (inArea(nextRow, nextCol) && board[nextRow][nextCol] == 'M') {
                mines++;
            }
        }

        if (mines > 0) board[row][col] = (char) (mines + '0');
        else {
            board[row][col] = 'B';
            for (int[] dir : dirs) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];
                dfs(nextRow, nextCol);
            }
        }
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }


}
