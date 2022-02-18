package com.douma._19_day_DFS_BFS._994;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _994_rotting_oranges {
    /* 994. 腐烂的橘子
    在给定的网格中，每个单元格可以有以下三个值之一：
    值 0 代表空单元格；
    值 1 代表新鲜橘子；
    值 2 代表腐烂的橘子。

    每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。

    返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。

    输入：[[2,1,1],
          [1,1,0],
          [0,1,1]]
    输出：4

    输入：[[2,1,1],
          [0,1,1],
          [1,0,1]]
    输出：-1
     */

    // 多源广度优先遍历
    public int orangesRotting(int[][] grid) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int rows = grid.length;
        int cols = grid[0].length;

        // 优化：现在使用二维数组的行索引 + 列索引转换成一维数组中的索引作为 Map 的 key
        Map<Integer, Integer> levelsMap = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 2) {
                    int index = row * cols + col;
                    queue.offer(index);
                    levelsMap.put(index, 0);
                }
            }
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            int currIndex = queue.poll();
            int row = currIndex / cols;
            int col = currIndex % cols;
            for (int[] dir : dirs) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];

                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                        && grid[nextRow][nextCol] == 1) {
                    grid[nextRow][nextCol] = 2;
                    int index = nextRow * cols + nextCol;
                    queue.offer(index);

                    // 更新所属层信息
                    int level = levelsMap.get(currIndex) + 1;
                    levelsMap.put(index, level);

                    ans = level;
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) return -1;
            }
        }

        return ans;
    }
}
