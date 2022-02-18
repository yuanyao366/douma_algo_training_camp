package com.douma.笔试代码.meituan._20210404;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
// issue 讨论：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4CJHD
public class _3_Jumping_Squares {

    private static int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private static int ans = Integer.MAX_VALUE;
    private static int k;
    private static int n;

    public static void main(String[] args) {
        // 1. 输入数据解析
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();

        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        // 2. 使用 floodfill 算法求解最小花费
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) { // 必须从值等于 1 的元素开始
                    boolean[][] visited = new boolean[n][n];
                    dfs(grid, row, col, visited, 0);
                }
            }
        }
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    private static void dfs(int[][] grid,
                            int row, int col,
                            boolean[][] visited, int res) {
        // 1. 如果已经走到了 k，说明找到了一条路径，那么更新结果
        if (grid[row][col] == k) {
            ans = Math.min(ans, res);
            return;
        }

        // 2. 上下左右四个方式不断的去尝试跳
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow >= 0 && nextRow < n
                    && nextCol >= 0 && nextCol < n
                    && !visited[nextRow][nextCol]
                    && grid[nextRow][nextCol] == grid[row][col] + 1) {
                visited[nextRow][nextCol] = true;
                // 计算两个点的曼哈顿距离
                int mahadunDis = Math.abs(nextRow - row) + Math.abs(nextCol - col);
                dfs(grid, nextRow, nextCol, visited, res + mahadunDis);
            }
        }
    }
}
